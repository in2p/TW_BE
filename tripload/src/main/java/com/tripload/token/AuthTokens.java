package com.tripload.token;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthTokens {
    private String accessToken;
    private String refreshToken;
    private String grantType;
    private Long accessExpiresIn;
    private Long refreshExpiresIn;
    public static AuthTokens of(String accessToken, String refreshToken, String grantType, Long accessExpiresIn, Long refreshExpiresIn) {
        return new AuthTokens(accessToken, refreshToken, grantType, accessExpiresIn, refreshExpiresIn);
    }
}
