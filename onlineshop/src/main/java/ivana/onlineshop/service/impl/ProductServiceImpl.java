package ivana.onlineshop.service.impl;

import ivana.onlineshop.controller.model.ProductDTO;
import ivana.onlineshop.entity.Product;
import ivana.onlineshop.mapper.ProductMapper;
import ivana.onlineshop.repository.ProductRepository;
import ivana.onlineshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @Override
    public void save(ProductDTO productDTO) {
        productRepository.save(productMapper.toEntity(productDTO));
    }

    @Override
    public Optional<ProductDTO> findById(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(productMapper::fromEntity);
    }

    @Override
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> findByQuantityGreaterThan(Long quantity) {
        return productRepository.findByQuantityGreaterThan(quantity)
                .stream()
                .map(productMapper::fromEntity)
                .toList();
    }
}
