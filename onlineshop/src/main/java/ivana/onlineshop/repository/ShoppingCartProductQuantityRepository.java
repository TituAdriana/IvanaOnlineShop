package ivana.onlineshop.repository;

import ivana.onlineshop.entity.Product;
import ivana.onlineshop.entity.ShoppingCartProductQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartProductQuantityRepository extends JpaRepository<ShoppingCartProductQuantity, Long> {

    @Query(value = "SELECT new ivana.onlineshop.entity.Product(p.id, p.name, p.size, p.price, s.quantity) from Product p inner join ShoppingCartProductQuantity s " +
            "on p.id = s.productId where shoppingCartId = :id")
    List<Product> getProductsByShoppingCartId(Long id);

    Optional<ShoppingCartProductQuantity> findByShoppingCartIdAndProductId(Integer shoppingCartId, Integer productId);

    @Modifying
    @Transactional
    void deleteByShoppingCartIdAndProductId(Integer shoppingCartId, Integer productId);

    @Modifying
    @Transactional
    void deleteByShoppingCartId(Integer shoppingCartId);
}
