package com.ait.project.template.modules.user.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class CreateUserOBTRequestDTO extends UserOBTRequestDTO {

	@NotBlank(message = "{body.required}")
	@JsonProperty("user_name")
	private String userName;

	@NotBlank(message = "{body.required}")
	@JsonProperty("user_address")
	private String userAddress;

	@NotBlank(message = "{body.required}")
	@Email(message = "{body.required}")
	@JsonProperty("user_email")
	private String userEmail;

	@NotBlank(message = "{body.required}")
	@JsonProperty("user_password")
	private String userPassword;
}
