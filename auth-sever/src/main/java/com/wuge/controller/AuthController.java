package com.wuge.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.security.KeyPair;
import java.security.Principal;
import java.security.interfaces.RSAPublicKey;
import java.util.Collections;
import java.util.Map;

@RestController
public class AuthController {
    @Autowired
    private KeyPair keyPair;
    @Autowired
    private TokenEndpoint tokenEndpoint;
    @GetMapping("/getPublicKey")
    public Map<String, Object> getPublicKey(HttpRequest request) {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }
//    @RequestMapping(value = "/oauth/token", method=RequestMethod.POST)
//    public ResponseEntity<OAuth2AccessToken> postAccessToken(Principal principal, @RequestParam
//            Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
//        ResponseEntity<OAuth2AccessToken> oAuth2AccessTokenResponseEntity = tokenEndpoint.postAccessToken(principal, parameters);
//        OAuth2AccessToken token = oAuth2AccessTokenResponseEntity.getBody();
//
//
//    }
}
