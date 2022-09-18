package balyasnikov.nikolay.computerstore.infrastructure.repository;

import balyasnikov.nikolay.computerstore.domain.entity.DesktopComputer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesktopComputerRepository extends JpaRepository<DesktopComputer, Long> {
}
