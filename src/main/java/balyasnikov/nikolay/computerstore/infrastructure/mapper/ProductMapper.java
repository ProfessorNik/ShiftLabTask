package balyasnikov.nikolay.computerstore.infrastructure.mapper;

import balyasnikov.nikolay.computerstore.domain.entity.Product;
import balyasnikov.nikolay.computerstore.domain.value.ProductType;
import balyasnikov.nikolay.computerstore.infrastructure.dto.ProductDto;

public abstract class ProductMapper {
    public static ProductMapper byType(ProductType productType) {
        return switch (productType) {
            case LAPTOP -> new LaptopMapper();
            case MONITOR -> new MonitorMapper();
            case HDD -> new HardDriveMapper();
            case DESKTOP_COMPUTER -> new DesktopComputerMapper();
        };
    }

    public void fillFromDto(Product product, ProductDto dto) {
        product.setCost(dto.getCost());
        product.setManufacturer(dto.getManufacturer());
        product.setSeriesNumber(dto.getSeriesNumber());
        product.setQuantity(dto.getQuantity());
    }

    public void updateFromDto(Product product, ProductDto dto) {
        if (dto.getCost() != null) {
            product.setCost(dto.getCost());
        }
        if (dto.getQuantity() != null) {
            product.setQuantity(dto.getQuantity());
        }
        if (dto.getManufacturer() != null) {
            product.setManufacturer(dto.getManufacturer());
        }
        if (dto.getSeriesNumber() != null) {
            product.setSeriesNumber(dto.getSeriesNumber());
        }
    }
}
