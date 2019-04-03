package softwire.training.myface.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import softwire.training.myface.services.UsersService;

@Service
public class MyFaceUserDetailsService implements UserDetailsService {

    private final UsersService usersService;

    @Autowired
    public MyFaceUserDetailsService(UsersService usersService) {
        this.usersService = usersService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserPrincipal(usersService.getUserByUsername(username));
    }
}
