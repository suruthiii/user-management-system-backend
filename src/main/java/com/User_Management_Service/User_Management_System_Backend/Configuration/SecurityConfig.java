package com.User_Management_Service.User_Management_System_Backend.Configuration;

import com.User_Management_Service.User_Management_System_Backend.Service.UserDetailsService;
import com.User_Management_Service.User_Management_System_Backend.Service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsService ourUserDetailsService;
    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, UserRoleService userRoleService) throws Exception {
        List<String> readAccess = userRoleService.getRoleByPermission("READ");
        List<String> createAccess = userRoleService.getRoleByPermission("CREATE");
        List<String> updateAccess = userRoleService.getRoleByPermission("UPDATE");
        List<String> deleteAccess = userRoleService.getRoleByPermission("DELETE");

        if (readAccess.isEmpty()) {
            readAccess = List.of("ADMIN");
        }
        if (createAccess.isEmpty()) {
            createAccess = List.of("ADMIN");
        }
        if (updateAccess.isEmpty()) {
            updateAccess = List.of("ADMIN");
        }
        if (deleteAccess.isEmpty()) {
            deleteAccess = List.of("ADMIN");
        }

        List<String> finalUpdateAccess = updateAccess;
        List<String> finalReadAccess = readAccess;
        List<String> finalDeleteAccess = deleteAccess;
        List<String> finalCreateAccess = createAccess;

        System.out.println("CREATE " + finalCreateAccess);
        System.out.println("DELETE " + finalDeleteAccess);
        System.out.println("UPDATE " + finalUpdateAccess);
        System.out.println("READ " + finalReadAccess);
        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/auth/**", "/public/**").permitAll()
                        .requestMatchers("/api/permissions/**").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/userRoles/**").hasAnyAuthority("ADMIN")
                        .requestMatchers("/swagger-ui/**", "/api-docs/**").permitAll()
                        .requestMatchers("/api/users/view/**").hasAnyAuthority(finalReadAccess.toArray(new String[0]))
                        .requestMatchers("/api/users/update/**").hasAnyAuthority(finalUpdateAccess.toArray(new String[0]))
                        .requestMatchers("/api/users/delete/**").hasAnyAuthority(finalDeleteAccess.toArray(new String[0]))
                        .requestMatchers("/api/users/create/**").hasAnyAuthority(finalCreateAccess.toArray(new String[0]))
                        .anyRequest().authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider()).addFilterBefore(
                        jwtAuthFilter, UsernamePasswordAuthenticationFilter.class
                ).build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(ourUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}

