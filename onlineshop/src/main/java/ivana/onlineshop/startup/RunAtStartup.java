package ivana.onlineshop.startup;

import ivana.onlineshop.entity.MyUser;
import ivana.onlineshop.entity.Role;
import ivana.onlineshop.entity.ShoppingCart;
import ivana.onlineshop.repository.ProductRepository;
import ivana.onlineshop.repository.RoleRepository;
import ivana.onlineshop.repository.ShoppingCartProductQuantityRepository;
import ivana.onlineshop.repository.UserRepository;
import ivana.onlineshop.service.UserService;
import ivana.onlineshop.service.impl.ShoppingCartServiceImpl;
import ivana.onlineshop.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class RunAtStartup {

    private final UserService userService;

    private final RoleRepository roleRepository;

    private final ProductRepository productRepository;

    private final ShoppingCartServiceImpl shoppingCartService;

    private final UserRepository userRepository;

    private final ShoppingCartProductQuantityRepository quantityRepository;


    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() {

        roleRepository.save(new Role(Constants.ROLE_USER));
        roleRepository.save(new Role(Constants.ROLE_ADMIN));

        saveUser();
        saveAdminUser();
    }

    private void saveAdminUser() {
        MyUser myUser = new MyUser();
        myUser.setUsername("admin");
        myUser.setPassword("admin");
        myUser.setRandomToken("randomToken");
        final Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(Constants.ROLE_ADMIN));
        myUser.setRoles(roles);
        myUser.setEnabled(true);
        myUser.setAccountNonExpired(true);
        myUser.setAccountNonLocked(true);
        myUser.setCredentialsNonExpired(true);
        myUser.setEmail("admin1@gmail.com");
        myUser.setFullName("Ivana Admin");
        myUser.setPasswordConfirm("admin");
        myUser.setRandomTokenEmail("randomToken");

        userService.saveUser(myUser);
    }

    public void saveUser() {
        MyUser myUser = new MyUser();
        myUser.setUsername("user");
        myUser.setPassword("user");
        myUser.setRandomToken("randomToken");
        final Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(Constants.ROLE_USER));
        myUser.setRoles(roles);
        myUser.setEnabled(true);
        myUser.setAccountNonExpired(true);
        myUser.setAccountNonLocked(true);
        myUser.setCredentialsNonExpired(true);
        myUser.setEmail("user@gmail.com");
        myUser.setFullName("Ivana User");
        myUser.setPasswordConfirm("user");
        myUser.setRandomTokenEmail("randomToken");

        MyUser myUser1 = userService.saveUser(myUser);

        ShoppingCart cart = myUser1.getShoppingCart();
        cart.setUser(myUser1);
        userService.updateUser(myUser1);

    }
}
