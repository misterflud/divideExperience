package com.divide.experience.lib.security.configuration.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 */
@FeignClient(contextId = "UserDetailsService", name = "auth-service")
@SuppressWarnings("checkstyle:javadocmethod")
public interface UserDetailsFeignClient {

    //TODO_: отрефакторить ендпоинты в гейтвее
    @GetMapping(path = "/api/users/{id}")
    UserDetailsResponse getUserDetails(@PathVariable("id") Long id);
}
