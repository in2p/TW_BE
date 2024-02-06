package com.tripload.service;

import com.tripload.jwt.JwtAuthenticationFilter;
import com.tripload.jwt.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class OAuthLogoutService {
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate<String, Object> redisTemplate;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public void logout(HttpServletRequest request){
        // 로그아웃 하고 싶은 토큰이 유효한 지 먼저 검증하기
        String token = jwtAuthenticationFilter.resolveToken(request);
        if (!jwtTokenProvider.validateToken(token)){
            throw new IllegalArgumentException("로그아웃 : 유효하지 않은 토큰입니다.");
        }

        // Access Token에서 User id을 가져온다
        Authentication authentication = jwtTokenProvider.getAuthentication(token);

        // Redis에서 해당 User_id로 저장된 Refresh Token 이 있는지 여부를 확인 후에 있을 경우 삭제를 한다.
        if (redisTemplate.opsForValue().get("RT:"+authentication.getAuthorities())!=null){
            // Refresh Token을 삭제
            redisTemplate.delete("RT:"+authentication.getName());
        }

        // 해당 Access Token 유효시간을 가지고 와서 BlackList에 저장하기
        Long expiration = jwtTokenProvider.getExpiration(token);
        redisTemplate.opsForValue().set(token,"logout",expiration, TimeUnit.MILLISECONDS);

    }
}
