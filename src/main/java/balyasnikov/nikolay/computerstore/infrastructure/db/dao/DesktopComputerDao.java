package balyasnikov.nikolay.computerstore.infrastructure.db.dao;

import balyasnikov.nikolay.computerstore.domain.entity.DesktopComputer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesktopComputerDao extends JpaRepository<DesktopComputer, Long> {
}
