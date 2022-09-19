package balyasnikov.nikolay.computerstore.infrastructure.db.dao;

import balyasnikov.nikolay.computerstore.domain.entity.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonitorDao extends JpaRepository<Monitor, Long> {
}
