package com.example.unikomwebresfulapi.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Data
public class UserPrincipal implements UserDetails {

    private Long id;

    private String username;

    private String password;

    private Collection<? extends GrantedAuthority> roles;

    public UserPrincipal() {
    }

    public UserPrincipal(Long id, String username, String password, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public static UserDetails build(User user) {
        Set<Role> roles = user.getRoles();
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(a -> authorities.add(new SimpleGrantedAuthority(a.getName())));
        UserDetails userDetails = new UserPrincipal(user.getId(), user.getUsername(), user.getPassword(), authorities);
        return userDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
}
