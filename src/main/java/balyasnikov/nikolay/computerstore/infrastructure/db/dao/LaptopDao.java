package balyasnikov.nikolay.computerstore.infrastructure.db.dao;

import balyasnikov.nikolay.computerstore.domain.entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaptopDao extends JpaRepository<Laptop, Long> {
}
