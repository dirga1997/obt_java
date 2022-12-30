package com.ait.project.template.modules.auth.service.internal.impl;

import com.ait.project.template.config.security.jwt.util.JwtUtil;
import com.ait.project.template.modules.auth.dto.request.LoginRequestDTO;
import com.ait.project.template.modules.auth.dto.response.LoginResponseDTO;
import com.ait.project.template.modules.auth.service.internal.AuthService;
import com.ait.project.template.modules.auth.transform.AuthTransform;
import com.ait.project.template.modules.user.model.entity.UserOBT;
import com.ait.project.template.modules.user.service.delegate.impl.UserOBTDelegateImpl;
import com.ait.project.template.shared.constant.enums.ResponseEnum;
import com.ait.project.template.shared.dto.template.ResponseDetail;
import com.ait.project.template.shared.dto.template.ResponseTemplate;
import com.ait.project.template.shared.utils.ResponseHelper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ResponseHelper responseHelper;
    private final AuthTransform authTransform;

    @Autowired
    private final UserOBTDelegateImpl userOBTDelegate;

    @Autowired
    AuthenticationManager authenticationManager;

    JwtUtil jwtUtil;
    @Override
    public ResponseEntity<ResponseTemplate<ResponseDetail<LoginResponseDTO>>> login(LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        UserOBT userOBT = userOBTDelegate.getUserByEmail(loginRequestDTO.getEmail());
        System.out.println(1);
        System.out.println(HttpStatus.NOT_FOUND);
        if (userOBT == null) {
            System.out.println(2);
            return responseHelper.createResponseDetail(ResponseEnum.USER_OBT_NOT_FOUND,
                    authTransform.createAuthResponse(loginResponseDTO));
        }
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(userOBT.getUserEmail(), userOBT.getUserPassword()
                        ));
        UserDetails userDetails = userOBTDelegate.loadUserByUsername(loginRequestDTO.getEmail());.
        final String jwt = jwtUtil.generateToken(userDetails);
        loginResponseDTO.setAccessToken(jwt);

        return responseHelper.createResponseDetail(ResponseEnum.SUCCESS,
                authTransform.createAuthResponse(loginResponseDTO));
    }
}
