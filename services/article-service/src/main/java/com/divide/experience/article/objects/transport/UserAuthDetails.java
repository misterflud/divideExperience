package com.divide.experience.article.objects.transport;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 */
@ApiModel(description = "The DTO for takes information about the user from auth service.")
public class UserAuthDetails implements Serializable {

    private static final long serialVersionUID = 71172115718292583L;

    @ApiModelProperty(value = "User's name (email). In future, it will be changed", example = "yvacia@yandex.ru")
    private String userName;

    @ApiModelProperty("Roles")
    private Set<String> roles;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
