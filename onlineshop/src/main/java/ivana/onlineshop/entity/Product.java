package ivana.onlineshop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String size;
    private float price;

    private Integer quantity;

    public Product(String name, String size, float price, Integer quantity) {
        this.name = name;
        this.size = size;
        this.price = price;
        this.quantity = quantity;
    }
}
