package balyasnikov.nikolay.computerstore.infrastructure.repository;

import balyasnikov.nikolay.computerstore.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    Optional<Product> findById(Long id);

    @Override
    void deleteById(Long id);
}
