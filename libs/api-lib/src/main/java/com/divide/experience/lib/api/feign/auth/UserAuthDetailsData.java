package com.divide.experience.lib.api.feign.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 */
@Builder
@Getter
@Setter
@ApiModel(description = "User detail. It is like an object from Spring Security but for REST communication.")
public class UserAuthDetailsData implements Serializable {

    private static final long serialVersionUID = 5643796194174671197L;

    @ApiModelProperty(value = "id", example = "6")
    private Long id;

    @ApiModelProperty(value = "Login", example = "vasia@yandex.ru")
    private String login;

    @ApiModelProperty(value = "User name", example = "Vasia")
    private String userName;

    @ApiModelProperty("Set of roles")
    private Set<String> roles;
}
