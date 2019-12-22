package com.divide.experience.auth.security;

/**
 * @author Anton Oleynikov {@literal <aoleynikov@fil-it.ru>}
 */
public interface TokenProcessor {

    String getUserFromToken(String token);

    String generateToken(String login);
}
