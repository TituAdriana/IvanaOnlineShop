package ivana.onlineshop.mapper;

import ivana.onlineshop.controller.model.ProductDTO;
import ivana.onlineshop.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDTO fromEntity(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getSize(), product.getPrice(), product.getQuantity());
    }

    public Product toEntity(ProductDTO productDTO) {
        return new Product(productDTO.getName(), productDTO.getSize(), productDTO.getPrice(), productDTO.getQuantity());
    }
}
