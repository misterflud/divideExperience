package com.divide.experience.lib.security.configuration.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 *  <p>
 *     Compact information object about user.
 *  </p>
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class UserSecurityDetails {

    private static final long serialVersionUID = -4768225410420280056L;

    private Long id;

    private String login;

    private Set<String> roles;
}
