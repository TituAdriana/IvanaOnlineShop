package ivana.onlineshop.repository;


import ivana.onlineshop.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
}
