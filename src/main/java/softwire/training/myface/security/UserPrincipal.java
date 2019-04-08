package softwire.training.myface.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import softwire.training.myface.models.dbmodels.User;

import java.util.ArrayList;
import java.util.Collection;

public class UserPrincipal implements UserDetails {

    private User user;

    public UserPrincipal(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }


    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }


    public String getPassword() {
        return user.getPassword();
    }


    public String getUsername() {
        return user.getUsername();
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



    public boolean isEnabled() {
        return true;
    }
}
