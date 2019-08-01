package edu.sjtu.naocemis.authentication.oauth2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import java.util.Arrays;

/**
 * configuration for OpenId
 */
@Configuration
@EnableOAuth2Client
public class SJTUOpenIdConnectConfig {

    @Value("${auth.client_id}")
    private String clientId;

    @Value("${auth.client_secret}")
    private String clientSecret;

    @Value("${auth.oauth2.token}")
    private String accessTokenUri;

    @Value("${auth.oauth2.authorize}")
    private String userAuthorizationUri;

    @Value("${auth.redirectUri}")
    private String redirectUri;

    @Value("${auth.logout_location}")
    private String logoutLocation;


    @Bean
    public OAuth2ProtectedResourceDetails sjtuOpenId() {

        AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
        details.setClientId(clientId);
        details.setClientSecret(clientSecret);
        details.setAccessTokenUri(accessTokenUri);
        details.setUserAuthorizationUri(userAuthorizationUri);
        details.setScope(Arrays.asList("openid","basic"));
//        details.setPreEstablishedRedirectUri(redirectUri);
//        details.setUseCurrentUri(false);
        return details;
    }


    @Bean
    public OAuth2RestTemplate sjtuOpenIdTemplate(OAuth2ClientContext clientContext) {
        return new OAuth2RestTemplate(sjtuOpenId(), clientContext);
    }

}
