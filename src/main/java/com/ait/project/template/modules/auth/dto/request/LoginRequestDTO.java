package com.ait.project.template.modules.auth.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class LoginRequestDTO {

    @NotBlank
    @JsonProperty("user_email")
    private String email;

    @NotBlank
    @JsonProperty("user_password")
    private String password;
}
