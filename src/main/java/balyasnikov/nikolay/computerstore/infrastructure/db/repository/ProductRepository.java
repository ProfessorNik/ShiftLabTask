package balyasnikov.nikolay.computerstore.infrastructure.db.repository;

import balyasnikov.nikolay.computerstore.application.getaway.ProductGetaway;
import balyasnikov.nikolay.computerstore.domain.entity.Product;
import balyasnikov.nikolay.computerstore.domain.value.ProductType;
import balyasnikov.nikolay.computerstore.infrastructure.db.dao.*;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ProductRepository implements ProductGetaway {
    private DesktopComputerDao desktopComputerDao;
    private HardDriveDao hardDriveDao;
    private LaptopDao laptopDao;
    private MonitorDao monitorDao;
    private ProductDao productDao;

    public List<?> findProductsByType(@NonNull ProductType productType) {
        return switch (productType) {
            case MONITOR -> monitorDao.findAll();
            case LAPTOP -> laptopDao.findAll();
            case HDD -> hardDriveDao.findAll();
            case DESKTOP_COMPUTER -> desktopComputerDao.findAll();
        };
    }

    public Product save(@NonNull Product product) {
        return productDao.save(product);
    }

    public Optional<Product> findById(@NonNull Long id) {
        return productDao.findById(id);
    }

    public void deleteById(@NonNull Long id) {
        productDao.deleteById(id);
    }

}
