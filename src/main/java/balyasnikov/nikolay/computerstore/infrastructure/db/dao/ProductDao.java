package balyasnikov.nikolay.computerstore.infrastructure.db.dao;

import balyasnikov.nikolay.computerstore.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductDao extends JpaRepository<Product, Long> {
    @Override
    Optional<Product> findById(Long id);

    @Override
    void deleteById(Long id);
}
