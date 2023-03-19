package ivana.onlineshop.controller;

import ivana.onlineshop.entity.MyUser;
import ivana.onlineshop.entity.Order;
import ivana.onlineshop.entity.Product;
import ivana.onlineshop.repository.OrderRepository;
import ivana.onlineshop.repository.ProductRepository;
import ivana.onlineshop.repository.ShoppingCartProductQuantityRepository;
import ivana.onlineshop.service.UserService;
import ivana.onlineshop.service.impl.ShoppingCartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/shopping-cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartServiceImpl shoppingCartService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCartProductQuantityRepository quantityRepository;

    @RequestMapping(value = "/to-order")
    public String convertToOrder(Model model) {

        //stabilim care e username-ul user-ului autentificat
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = auth.getName();

        //aducem userul din db pe baza username-ului
        MyUser user = userService.findUserByUserName(currentPrincipalName);

        List<Product> productsByShoppingCartId = quantityRepository.getProductsByShoppingCartId(user.getId());
        shoppingCartService.findById(user.getId().intValue()).ifPresent(cart -> {
            cart.setProducts(productsByShoppingCartId);
            user.setShoppingCart(cart);
        });

        Order order = orderRepository.save(shoppingCartService.convertShoppingCartToOrder(user.getShoppingCart()));
        user.getShoppingCart().getProducts().clear();
        quantityRepository.deleteByShoppingCartId(user.getId().intValue());
        model.addAttribute("order", order);
        return "order-details";
    }

    @RequestMapping
    public String getShoppingCartForPrincipal(Model model) {
        //stabilim care e username-ul user-ului autentificat
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = auth.getName();

        //aducem userul din db pe baza username-ului
        MyUser userByUserName = userService.findUserByUserName(currentPrincipalName);

        List<Product> productsByShoppingCartId = quantityRepository.getProductsByShoppingCartId(userByUserName.getId());


        model.addAttribute("products", productsByShoppingCartId);

        return "shopping-cart";
    }
}
