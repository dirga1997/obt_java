package com.ait.project.template.shared.openfeign.user;

import com.ait.project.template.modules.user.dto.response.UserOBTResponseDTO;
import com.ait.project.template.shared.openfeign.user.request.CreateUserOBTRequest;
import com.ait.project.template.shared.openfeign.user.request.UpdateUserOBTRequest;
import com.ait.project.template.shared.openfeign.user.response.UserOBTResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        value = "user-client",
        url = "${restclient.url.user-client}",
        fallback = UserOBTClientFallback.class
)
public interface UserOBTClient {
    @GetMapping("/user/get/all")
    List<UserOBTResponseDTO> getListUser();

    @PostMapping("/user/create")
    UserOBTResponse createUser(@RequestBody CreateUserOBTRequest createUserOBTRequest);

    @PostMapping("/user/update/{userId}")
    UserOBTResponse updateUser(@RequestBody UpdateUserOBTRequest updateUserRequest);
}
