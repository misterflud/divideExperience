package com.divide.experience.article.security;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 */
public interface TokenProcessor {

    String getUserFromToken(String token);

    String generateToken(String login);

    UserDetails getUserDetailsForService(String token);
}
