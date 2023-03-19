package ivana.onlineshop.service;

import ivana.onlineshop.entity.Order;
import ivana.onlineshop.entity.ShoppingCart;

import java.util.Optional;

public interface ShoppingCartService {

    Optional<ShoppingCart> findById(Integer id);

    Order convertShoppingCartToOrder(ShoppingCart shoppingCart);

    ShoppingCart update(ShoppingCart shoppingCart);

    ShoppingCart save(ShoppingCart sc);

    void deleteProductByIdFromShoppingCart(Integer productId);
}
