package com.divide.experience.auth.security;

import com.divide.experience.lib.api.feign.auth.UserAuthDetailsData;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 */
public interface TokenProcessor {

    UserAuthDetailsData getUserDataFromToken(String token);

    String generateToken(DivideUserDetails user);

    UserDetails getUserDetailsForService(String token);
}
