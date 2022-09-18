package balyasnikov.nikolay.computerstore.infrastructure.repository;

import balyasnikov.nikolay.computerstore.domain.entity.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitorRepository extends JpaRepository<Monitor, Long> {
}
