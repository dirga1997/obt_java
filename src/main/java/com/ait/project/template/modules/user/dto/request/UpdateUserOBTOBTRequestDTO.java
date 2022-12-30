package com.ait.project.template.modules.user.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.annotation.CheckForNull;
import javax.validation.constraints.Email;

@Data
public class UpdateUserOBTOBTRequestDTO extends UserOBTRequestDTO {

    @CheckForNull
    private long userId;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("user_address")
    private String userAddress;

    @Email(message = "{body.required}")
    @JsonProperty("user_email")
    private String userEmail;

    @JsonProperty("user_password")
    private String userPassword;
}
