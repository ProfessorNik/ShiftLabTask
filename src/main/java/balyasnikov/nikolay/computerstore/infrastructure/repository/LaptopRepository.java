package balyasnikov.nikolay.computerstore.infrastructure.repository;

import balyasnikov.nikolay.computerstore.domain.entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {
}
