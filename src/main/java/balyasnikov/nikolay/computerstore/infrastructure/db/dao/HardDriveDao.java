package balyasnikov.nikolay.computerstore.infrastructure.db.dao;

import balyasnikov.nikolay.computerstore.domain.entity.HardDrive;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HardDriveDao extends JpaRepository<HardDrive, Long> {
}
