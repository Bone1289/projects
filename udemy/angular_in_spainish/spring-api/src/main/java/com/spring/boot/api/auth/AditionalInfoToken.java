package com.spring.boot.api.auth;

import com.spring.boot.api.model.entity.User;
import com.spring.boot.api.model.service.IUserService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AditionalInfoToken implements TokenEnhancer {

    private final IUserService userService;

    public AditionalInfoToken(IUserService userService) {
        this.userService = userService;
    }


    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> info = new HashMap<>();
        User user = userService.findByUsername(authentication.getName());
        info.put("additional_info", "Hello ".concat(authentication.getName()));
        info.put("first_name", user.getFirstName());
        info.put("last_name", user.getLastName());
        info.put("email", user.getEmail());

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        return accessToken;
    }
}
