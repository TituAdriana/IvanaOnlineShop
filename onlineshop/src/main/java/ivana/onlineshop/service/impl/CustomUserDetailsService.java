package ivana.onlineshop.service.impl;

import ivana.onlineshop.entity.MyUser;
import ivana.onlineshop.entity.Role;
import ivana.onlineshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser = userService.findUserByUserName(username);
        List<GrantedAuthority> authorities = getUserAuthority(myUser.getRoles());
        return new MyUser(myUser.getUsername(), myUser.getPassword(),
                myUser.isEnabled(), myUser.isAccountNonExpired(), myUser.isCredentialsNonExpired(), myUser.isAccountNonLocked(), authorities);
    }

    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (Role role : userRoles) {
            roles.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new ArrayList<>(roles);
    }
}
