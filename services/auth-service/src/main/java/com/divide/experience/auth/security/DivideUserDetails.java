package com.divide.experience.auth.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DivideUserDetails implements UserDetails {

    private Collection<? extends GrantedAuthority> authorities;

    private Long id;

    private String email;

    private String nickName;

    private String password;

    private String username;

    private Boolean enabled = true;

    private Boolean accountNonExpired = true;

    private Boolean accountNonLocked = true;

    private boolean credentialsNonExpired = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return nickName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void eraseCredentials() {
        this.password = null;
    }
}
