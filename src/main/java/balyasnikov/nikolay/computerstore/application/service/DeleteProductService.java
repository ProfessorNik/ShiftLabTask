package balyasnikov.nikolay.computerstore.application.service;

import balyasnikov.nikolay.computerstore.application.getaway.ProductGetaway;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteProductService {
    private ProductGetaway productGetaway;

    public void delete(@NonNull Long id){
        productGetaway.deleteById(id);
    }
}
