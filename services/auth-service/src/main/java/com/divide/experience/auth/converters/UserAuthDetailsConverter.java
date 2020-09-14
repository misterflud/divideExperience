package com.divide.experience.auth.converters;


import com.divide.experience.lib.api.feign.auth.UserAuthDetailsData;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 */
@Component
public class UserAuthDetailsConverter implements Converter<UserDetails, UserAuthDetailsData> {

    @Override
    public UserAuthDetailsData convert(UserDetails userDetails) {
        Set<String> roles = new HashSet<>();
        userDetails.getAuthorities().forEach(a -> roles.add(a.getAuthority()));
        UserAuthDetailsData details = UserAuthDetailsData
            .builder()
            .userName(userDetails.getUsername())
            .roles(roles)
            .build();
        return details;
    }
}
