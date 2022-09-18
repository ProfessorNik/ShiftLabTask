package balyasnikov.nikolay.computerstore.infrastructure.repository;

import balyasnikov.nikolay.computerstore.domain.entity.HardDrive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HardDriveRepository extends JpaRepository<HardDrive, Long> {
}
