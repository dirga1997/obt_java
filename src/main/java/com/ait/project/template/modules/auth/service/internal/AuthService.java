package com.ait.project.template.modules.auth.service.internal;

import com.ait.project.template.modules.auth.dto.request.LoginRequestDTO;
import com.ait.project.template.modules.auth.dto.response.LoginResponseDTO;
import com.ait.project.template.shared.dto.template.ResponseDetail;
import com.ait.project.template.shared.dto.template.ResponseTemplate;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<ResponseTemplate<ResponseDetail<LoginResponseDTO>>> login(LoginRequestDTO loginRequestDTO);
}
