package com.ait.project.template.shared.openfeign.user.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateUserOBTRequest {
    @JsonProperty("user_id")
    private long userId;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("user_address")
    private String userAddress;

    @JsonProperty("user_email")
    private String userEmail;
}
