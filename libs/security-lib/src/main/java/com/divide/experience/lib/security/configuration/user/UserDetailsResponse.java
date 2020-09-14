package com.divide.experience.lib.security.configuration.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * DTO for user communication.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class UserDetailsResponse {

    private static final long serialVersionUID = -8696232039069431901L;

    //    @ApiModelProperty("Информация о пользователе")
    private UserDetails user;

    @Builder
    public UserDetailsResponse(UserDetails user) {
        this.user = user;
    }

}
