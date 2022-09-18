package balyasnikov.nikolay.computerstore.application.service;

import balyasnikov.nikolay.computerstore.infrastructure.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteProductService {
    private ProductRepository repository;

    public void delete(@NonNull Long id){
        repository.deleteById(id);
    }
}
