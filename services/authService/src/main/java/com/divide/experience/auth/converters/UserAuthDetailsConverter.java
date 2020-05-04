package com.divide.experience.auth.converters;


import com.divide.experience.auth.objects.transport.UserAuthDetails;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 */
@Component
public class UserAuthDetailsConverter implements Converter<UserDetails, UserAuthDetails> {

    @Override
    public UserAuthDetails convert(UserDetails userDetails) {
        UserAuthDetails details = new UserAuthDetails();
        details.setUserName(userDetails.getUsername());
        Set<String> roles = new HashSet<>();
        userDetails.getAuthorities().forEach(a -> roles.add(a.getAuthority()));
        details.setRoles(roles);
        return details;
    }
}
