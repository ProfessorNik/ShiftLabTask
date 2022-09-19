package balyasnikov.nikolay.computerstore.application.service;

import balyasnikov.nikolay.computerstore.application.exception.InvalidDataException;
import balyasnikov.nikolay.computerstore.application.getaway.ProductGetaway;
import balyasnikov.nikolay.computerstore.domain.entity.Product;
import balyasnikov.nikolay.computerstore.domain.value.ProductType;
import balyasnikov.nikolay.computerstore.infrastructure.dto.ProductDto;
import balyasnikov.nikolay.computerstore.infrastructure.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddProductService {
    public ProductGetaway productGetaway;

    public Product addProduct(ProductDto productDto, ProductType productType) {
        try {
            var product = Product.createByType(productType);
            ProductMapper.byType(productType)
                    .fillFromDto(product, productDto);

            return productGetaway.save(product);
        } catch (NullPointerException e) {
            throw new InvalidDataException("Not all data for product creation was transferred", e);
        } catch (IllegalArgumentException e) {
            throw new InvalidDataException("The product cannot be updated because, " + e.getMessage(), e);
        }
    }
}