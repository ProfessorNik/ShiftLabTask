package balyasnikov.nikolay.computerstore.application.service;

import balyasnikov.nikolay.computerstore.application.getaway.ProductGetaway;
import balyasnikov.nikolay.computerstore.domain.value.ProductType;
import balyasnikov.nikolay.computerstore.domain.entity.Product;
import balyasnikov.nikolay.computerstore.infrastructure.dto.ProductDto;
import balyasnikov.nikolay.computerstore.infrastructure.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateProductService {
    private ProductGetaway productGetaway;

    public Product updateProduct(@NonNull Long id, @NonNull ProductDto dto, @NonNull ProductType productType){
        var productOpt = productGetaway.findById(id);
        var product = productOpt.orElseThrow();
        ProductMapper.byType(productType)
                .updateFromDto(product, dto);
        return productGetaway.save(product);
    }
}
