package com.ait.project.template.modules.user.controller;

import com.ait.project.template.modules.user.dto.request.CreateUserOBTRequestDTO;
import com.ait.project.template.modules.user.dto.request.UpdateUserOBTRequestDTO;
import com.ait.project.template.modules.user.dto.response.UserOBTResponseDTO;
import com.ait.project.template.modules.user.service.internal.UserOBTService;
import com.ait.project.template.shared.dto.template.ResponseDetail;
import lombok.RequiredArgsConstructor;
import com.ait.project.template.shared.dto.template.ResponseList;
import com.ait.project.template.shared.dto.template.ResponseTemplate;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserOBTController implements UserOBTService {

    private final UserOBTService userOBTService;

    @Override
    @GetMapping("/user/get/all")
    public ResponseEntity<ResponseTemplate<ResponseList<UserOBTResponseDTO>>> getAllUser() {
        return userOBTService.getAllUser();
    }

    @Override
    @GetMapping("/user/get/id/{userId}")
    public ResponseEntity<ResponseTemplate<ResponseDetail<UserOBTResponseDTO>>> getUserById(@PathVariable(value = "userId") long userId) {
        return userOBTService.getUserById(userId);
    }

    @Override
    @GetMapping("/user/get/page")
    public ResponseEntity<ResponseTemplate<ResponseList<UserOBTResponseDTO>>> getAllUserPage(Pageable pageable) {
        return userOBTService.getAllUserPage(pageable);
    }

    @Override
    @PostMapping("/user/create")
    public ResponseEntity<ResponseTemplate<ResponseDetail<UserOBTResponseDTO>>> addUser(
            @Valid
            @RequestBody
            CreateUserOBTRequestDTO createUserOBTRequestDTO
    ) {
        return userOBTService.addUser(createUserOBTRequestDTO);
    }

    @Override
    @PutMapping("/user/update/{userId}")
    public ResponseEntity<ResponseTemplate<ResponseDetail<UserOBTResponseDTO>>> updateUser(
            @PathVariable(value = "userId")
            long userId,
            @Valid
            @RequestBody
            UpdateUserOBTRequestDTO updateUserRequestDTO
    ) {
        return userOBTService.updateUser(userId, updateUserRequestDTO);
    }

    @Override
    @DeleteMapping("/user/delete/{userId}")
    public ResponseEntity<ResponseTemplate<ResponseDetail<Boolean>>> deleteById(@PathVariable(value = "userId") long userId) {
        return userOBTService.deleteById(userId);
    }
}
