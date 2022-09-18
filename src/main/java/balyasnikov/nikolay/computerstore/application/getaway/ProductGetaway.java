package balyasnikov.nikolay.computerstore.application.getaway;

import balyasnikov.nikolay.computerstore.domain.value.ProductType;
import balyasnikov.nikolay.computerstore.domain.entity.Product;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface ProductGetaway {
    List<?> findProductsByType(@NonNull ProductType productType);

    Product save(@NonNull Product product);

    Optional<Product> findById(@NonNull Long id);

    void deleteById(@NonNull Long id);
}
