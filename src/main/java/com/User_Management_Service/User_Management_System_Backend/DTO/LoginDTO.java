package com.User_Management_Service.User_Management_System_Backend.DTO;

import com.User_Management_Service.User_Management_System_Backend.Validations.ValidEmail;
import com.User_Management_Service.User_Management_System_Backend.Validations.ValidPassword;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginDTO {
    @ValidEmail
    private String email;

    @ValidPassword
    private String password;
}
