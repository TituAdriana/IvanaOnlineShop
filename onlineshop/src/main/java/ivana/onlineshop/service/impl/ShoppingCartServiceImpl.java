package ivana.onlineshop.service.impl;

import ivana.onlineshop.entity.Order;
import ivana.onlineshop.entity.ShoppingCart;
import ivana.onlineshop.repository.ShoppingCartRepository;
import ivana.onlineshop.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    @Override
    public Optional<ShoppingCart> findById(Integer id) {
        return shoppingCartRepository.findById(id);
    }

    @Override
    public Order convertShoppingCartToOrder(ShoppingCart shoppingCart) {
        Order order = new Order();
        order.getProducts().addAll(shoppingCart.getProducts());
        order.setOrderDate(LocalDateTime.now());
        order.setUser(shoppingCart.getUser());
        return order;
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart save(ShoppingCart sc) {
        return shoppingCartRepository.save(sc);
    }

    @Override
    public void deleteProductByIdFromShoppingCart(Integer productId) {
        shoppingCartRepository.findAll().stream()
                .filter(cart -> cart.getProducts().removeIf(product -> product.getId().equals(productId)))
                .toList();
    }
}
