package ivana.onlineshop.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Integer id;

    private String name;

    private String size;

    private float price;

    private Integer quantity;
}
