package com.tripload.oauth;

import com.tripload.oauth.OAuthInfoResponse;
import com.tripload.oauth.OAuthLoginParams;
import com.tripload.oauth.OAuthProvider;

public interface OAuthApiClient {
    OAuthProvider oAuthProvider();
    String requestAccessToken(OAuthLoginParams params);
    OAuthInfoResponse requestOauthInfo(String accessToken);
}
