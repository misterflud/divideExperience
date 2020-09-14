package com.divide.experience.lib.security.configuration.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Все информация о пользователе (из jwt).
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class UserDetails {

    private static final long serialVersionUID = -4091950033895139092L;

    private Long id;

    private String login;
}
