package com.tripload.controller;

import com.tripload.kakao.KakaoLoginParams;
import com.tripload.service.OAuthLoginService;
import com.tripload.service.OAuthLogoutService;
import com.tripload.token.AuthTokens;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final OAuthLoginService oAuthLoginService;
    private final OAuthLogoutService oAuthLogoutService;

    @PostMapping("/kakao")
    public ResponseEntity<AuthTokens> loginKakao(@RequestBody KakaoLoginParams params) {
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<Void> logout(HttpServletRequest servletRequest) {
        oAuthLogoutService.logout(servletRequest);
        return ResponseEntity.ok().build();
    }
}
