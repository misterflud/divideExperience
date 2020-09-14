package com.divide.experience.auth.objects.transport;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 */
@ApiModel(description = "User detail. It is like an object from Spring Security but for REST communication.")
public class UserAuthDetails implements Serializable {

    private static final long serialVersionUID = 5643796194174671197L;

    @ApiModelProperty(value = "User name (email)", example = "vasia@yandex.ru")
    private String userName;

    @ApiModelProperty("Set of roles")
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
