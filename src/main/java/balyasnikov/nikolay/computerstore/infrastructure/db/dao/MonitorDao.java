package balyasnikov.nikolay.computerstore.infrastructure.db.dao;

import balyasnikov.nikolay.computerstore.domain.entity.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MonitorDao extends JpaRepository<Monitor, Long> {
}
