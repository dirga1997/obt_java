package com.ait.project.template.modules.auth.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponseDTO {

    @JsonProperty("acces_token")
    private String accessToken;
}
