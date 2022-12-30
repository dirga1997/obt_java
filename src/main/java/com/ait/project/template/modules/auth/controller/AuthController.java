package com.ait.project.template.modules.auth.controller;

import com.ait.project.template.modules.auth.dto.request.LoginRequestDTO;
import com.ait.project.template.modules.auth.dto.response.LoginResponseDTO;
import com.ait.project.template.modules.auth.service.internal.AuthService;
import com.ait.project.template.shared.dto.template.ResponseDetail;
import com.ait.project.template.shared.dto.template.ResponseTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthService {

    private final AuthService authService;

    @Override
    @PostMapping("/auth/login")
    public ResponseEntity<ResponseTemplate<ResponseDetail<String>>> login(
            @Valid
            @RequestBody
            LoginRequestDTO loginRequestDTO
    ) {
        return authService.login(loginRequestDTO);
    }
}
