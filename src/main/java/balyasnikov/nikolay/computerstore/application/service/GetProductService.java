package balyasnikov.nikolay.computerstore.application.service;

import balyasnikov.nikolay.computerstore.application.getaway.ProductGetaway;
import balyasnikov.nikolay.computerstore.domain.value.ProductType;
import balyasnikov.nikolay.computerstore.domain.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetProductService {
    public ProductGetaway productGetaway;

    public List<?> getProducts(ProductType productType){
        return productGetaway.findProductsByType(productType);
    }

    public Product getProductBy(Long id){
        return productGetaway.findById(id).orElseThrow();
    }

}
