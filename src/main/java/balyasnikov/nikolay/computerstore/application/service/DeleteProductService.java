package balyasnikov.nikolay.computerstore.application.service;

import balyasnikov.nikolay.computerstore.application.exception.ProductNotFoundException;
import balyasnikov.nikolay.computerstore.application.getaway.ProductGetaway;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteProductService {
    private ProductGetaway productGetaway;

    public void delete(@NonNull Long id){
        try {
            productGetaway.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ProductNotFoundException("product with this id was not found, it cannot be deleted", e);
        }
    }
}
