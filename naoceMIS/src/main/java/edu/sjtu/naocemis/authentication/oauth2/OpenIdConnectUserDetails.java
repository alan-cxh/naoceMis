package edu.sjtu.naocemis.authentication.oauth2;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class OpenIdConnectUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String username;
    private OAuth2AccessToken token;

    private List<GrantedAuthority> permissions;


    public OpenIdConnectUserDetails(Map<String, String> userInfo
            , OAuth2AccessToken token
            , List<GrantedAuthority>  permissions) {
        this.userId = userInfo.get("sub");
        this.username = userInfo.get("name");
        this.permissions = permissions;
        this.token = token;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled(){
        return true;
    }

    public String getPassword(){
        return null;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername(){
        return username;

    }

    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public OAuth2AccessToken getToken() {
        return token;
    }

    public void setToken(OAuth2AccessToken token) {
        this.token = token;
    }

    public List<GrantedAuthority> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<GrantedAuthority> permissions) {
        this.permissions = permissions;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions;
    }


}