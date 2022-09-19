package balyasnikov.nikolay.computerstore.application.service;

import balyasnikov.nikolay.computerstore.application.exception.ProductNotFoundException;
import balyasnikov.nikolay.computerstore.application.getaway.ProductGetaway;
import balyasnikov.nikolay.computerstore.domain.entity.Product;
import balyasnikov.nikolay.computerstore.domain.value.ProductType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class GetProductService {
    public ProductGetaway productGetaway;

    public List<?> getProducts(ProductType productType) {
        return productGetaway.findProductsByType(productType);
    }

    public Product getProductBy(Long id) {
        try {
            return productGetaway.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new ProductNotFoundException("Product with this id was not found", e);
        }
    }

}
