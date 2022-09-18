package balyasnikov.nikolay.computerstore.application.service;

import balyasnikov.nikolay.computerstore.application.getaway.ProductGetaway;
import balyasnikov.nikolay.computerstore.domain.value.ProductType;
import balyasnikov.nikolay.computerstore.domain.entity.*;
import balyasnikov.nikolay.computerstore.infrastructure.dto.*;

import balyasnikov.nikolay.computerstore.infrastructure.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddProductService {
    public ProductGetaway productGetaway;

    public Product addProduct(ProductDto productDto, ProductType productType){
        var product = Product.createByType(productType);
        ProductMapper.byType(productType)
                .fillFromDto(product, productDto);
        return productGetaway.save(product);
    }
}