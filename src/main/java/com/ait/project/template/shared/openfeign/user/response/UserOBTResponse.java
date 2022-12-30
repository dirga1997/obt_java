package com.ait.project.template.shared.openfeign.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOBTResponse {
    @JsonProperty("user_id")
    private long userId;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("user_address")
    private String userAddress;

    @JsonProperty("user_email")
    private String userEmail;
}
