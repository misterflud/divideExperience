package com.divide.experience.lib.security.configuration.service;

import net.minidev.json.JSONArray;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 */
public class JwtGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private static final String AUTHORITY_PREFIX = "ROLE_";
    private static final String AUTHORITIES_CLAIM_NAME = "roles";

    public JwtGrantedAuthoritiesConverter() {
    }

    /**
     * Конвертируем расшифрованный jwt в роли.
     *
     * @param jwt ключ
     * @return список ролей
     */
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList();

        for (String authority : this.getAuthorities(jwt)) {
            grantedAuthorities.add(new SimpleGrantedAuthority(AUTHORITY_PREFIX + authority));
        }

        return grantedAuthorities;
    }

    private String getAuthoritiesClaimName() {
        return AUTHORITIES_CLAIM_NAME;
    }

    private Collection<String> getAuthorities(Jwt jwt) {
        String claimName = this.getAuthoritiesClaimName();
        if (claimName == null) {
            return Collections.emptyList();
        } else {
            Object authorities = jwt.getClaim(claimName);
            return ((JSONArray) authorities).stream().map(Object::toString).collect(Collectors.toList());
        }
    }
}
