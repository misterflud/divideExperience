package com.divide.experience.lib.security.configuration;

import com.divide.experience.lib.security.configuration.user.UserDetails;
import com.divide.experience.lib.security.configuration.user.UserSecurityDetails;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Map;
import java.util.Optional;

/**
 * @author Anton Oleynikov {@literal <aoleynikov@fil-it.ru>}
 */
public final class SecurityHelper {

    private static final ObjectMapper MAPPER;

    static {
        MAPPER = new ObjectMapper();
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * Данные пользователя, которые приходят в сообщениях MQ-очередей.
     */
    @SuppressWarnings("checkstyle:ConstantName")
    private static final ThreadLocal<UserDetails> threadLocalUserDetails = new ThreadLocal<>();

    // Constructor ---
    private SecurityHelper() {

    }

    // Public methods ---

    /**
     * Получение информации о логине пользователя из JWT-токена.
     *
     * @return Информация по пользователю
     */
    public static Long userId() {
        Optional<UserSecurityDetails> userSecurityDetails = divideUserSecurityDetails();
        return userSecurityDetails.map(UserSecurityDetails::getId).orElse(null);
    }

    /**
     * Получение информации о пользователе из JWT-токена.
     *
     * @return Информация по пользователю
     */
    public static Optional<UserSecurityDetails> divideUserSecurityDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (isValidCredentials(authentication)) {
            JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;
            Map<String, Object> details = token.getTokenAttributes();
            return Optional.of(MAPPER.convertValue(details, UserSecurityDetails.class));
        }
        return Optional.empty();
    }

    /**
     * Получение расширенной информации о пользователе из JWT-токена.
     *
     * @return Информация по пользователю
     */
    public static Optional<UserDetails> userDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (isValidCredentials(authentication)) {
            JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;
            Map<String, Object> details = token.getTokenAttributes();
            return Optional.of(MAPPER.convertValue(details, UserDetails.class));
        }
        return Optional.empty();
    }

    /**
     * Сохранить Данные пользователя в ThreadLocal переменной. Используется в случаях,
     * когда недоступен веб-контекст. Например для запуска процесса из Rabbit-Listener-а.
     * Процесс следует запускать в отдельном потоке.
     *
     * @param userDetails данные пользователя
     */
    public static void setUserDetailsInThreadLocal(UserDetails userDetails) {
        threadLocalUserDetails.set(userDetails);
    }

    /**
     * Получить Данные пользователя, сохранённые в ThreadLocal переменной.
     *
     * @return данные пользователя
     */
    public static UserDetails getUserDetailsFromThreadLocal() {
        return threadLocalUserDetails.get();
    }

    /**
     * Получить идентификатор пользователя, сохранённый в ThreadLocal переменной.
     *
     * @return Идентификатор пользователя
     */
    public static Optional<Long> getUserIdFromThreadLocal() {
        if (threadLocalUserDetails.get() != null && threadLocalUserDetails.get().getId() != null) {
            return Optional.of(threadLocalUserDetails.get().getId());
        }
        return Optional.empty();
    }

    /**
     * Gets token value from jwt.
     *
     * @return token value
     */
    public static Optional<String> getTokenValue() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof JwtAuthenticationToken) {
            JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;
            return Optional.of(token.getToken().getTokenValue());
        }
        return Optional.empty();
    }

    // Private methods ---

    private static boolean isValidCredentials(Authentication authentication) {
        return !(authentication == null || authentication.getDetails() == null
            || !(authentication instanceof JwtAuthenticationToken));
    }
}
