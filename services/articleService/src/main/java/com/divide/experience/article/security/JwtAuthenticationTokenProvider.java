package com.divide.experience.article.security;

import com.divide.experience.article.objects.transport.UserAuthDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 */
@Service
public class JwtAuthenticationTokenProvider implements AuthenticationProvider {

    @Value("${auth.service.http.endpoint.user_details:http://localhost:7002/auth/user_details}")
    private String getUserDetailsUrl;

    @Value("${security.oauth2.article.jwt.key-value}")
    private String articleServiceSecret;

    public JwtAuthenticationTokenProvider() {
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "Authentication is missing");
        Assert.isInstanceOf(JwtAuthenticationToken.class, authentication, "This method only accepts JwtAuthenticationToken");
        final RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("token", authentication.getName());
        headers.add(AUTHORIZATION, articleServiceSecret);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        final ResponseEntity<UserAuthDetails> res = restTemplate.exchange(getUserDetailsUrl, HttpMethod.GET, entity, UserAuthDetails.class);
        if (res.getStatusCode().is2xxSuccessful() && res.getBody() != null) {
            return new UserTokenDetail(generateUserDetails(res.getBody()));
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JwtAuthenticationToken.class);
    }

    private UserDetails generateUserDetails(UserAuthDetails userAuthDetails) {
        Set<GrantedAuthority> roles = new HashSet<>();
        userAuthDetails.getRoles().forEach(r -> roles.add(new SimpleGrantedAuthority(r)));
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                userAuthDetails.getUserName(),
                "",
                roles);
        return userDetails;
    }
}
