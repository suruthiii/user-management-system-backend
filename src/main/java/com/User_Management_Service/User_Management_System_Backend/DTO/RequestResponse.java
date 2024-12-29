package com.User_Management_Service.User_Management_System_Backend.DTO;

import com.User_Management_Service.User_Management_System_Backend.Entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestResponse {
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private User userDetails;
}
