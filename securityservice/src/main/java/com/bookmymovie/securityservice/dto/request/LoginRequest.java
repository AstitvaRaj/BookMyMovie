package com.bookmymovie.securityservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginRequest {

    @NotBlank(message = "Provide email")
    String email;

    @NotBlank(message = "Provide password")
    String password;
}
