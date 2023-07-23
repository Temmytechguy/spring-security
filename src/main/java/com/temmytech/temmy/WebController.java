package com.temmytech.temmy;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author TemmyTechie
 */
@RestController
public class WebController {


    @GetMapping("/")
    public String publicMessage()
    {
        return "hello Temmy  ";

    }

    @GetMapping("/private")
    public String privateMessage(Authentication authentication)
    {
        return "Welcome to the VIP ROOM ~[ " +
                getName(authentication)
                + "] ~ ";

    }

    private static String getName(Authentication authentication)
    {
        return Optional.of(authentication.getPrincipal())
                .filter(OidcUser.class::isInstance)
                .map(OidcUser.class::cast)
                .map(OidcUser::getEmail)
                .orElseGet(authentication::getName);
    }


}
