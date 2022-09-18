package balyasnikov.nikolay.computerstore.application.service;

import balyasnikov.nikolay.computerstore.domain.ProductType;
import balyasnikov.nikolay.computerstore.domain.entity.*;
import balyasnikov.nikolay.computerstore.infrastructure.dto.*;
import balyasnikov.nikolay.computerstore.infrastructure.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateProductService {
    private ProductRepository repository;

    public Product updateProduct(@NonNull Long id, @NonNull ProductDto dto, @NonNull ProductType type){
        var productOpt = repository.findById(id);
        var product = productOpt.orElseThrow();
        setMainParams(product, dto);
        setAdvancedParams(product, dto, type);
        return product;
    }

    private void setMainParams(Product product, ProductDto dto){
        if(dto.getCost() != null){
            product.setCost(dto.getCost());
        }
        if(dto.getQuantity() != null){
            product.setQuantity(dto.getQuantity());
        }
        if(dto.getManufacturer() != null){
            product.setManufacturer(dto.getManufacturer());
        }
        if(dto.getSeriesNumber() != null){
            product.setSeriesNumber(dto.getSeriesNumber());
        }
    }

    private void setAdvancedParams(Product product, ProductDto dto, ProductType type){
        switch (type){
            case HDD -> {
                if(((HardDriveDto)dto).getCapacity() != null){
                    ((HardDrive)product).setCapacity(((HardDriveDto)dto).getCapacity());
                }
            }
            case LAPTOP -> {
                if(((LaptopDto)dto).getSize() != null){
                    ((Laptop)product).setSize(((LaptopDto)dto).getSize());
                }
            }
            case MONITOR -> {
                if(((MonitorDto)dto).getDiagonal() != null){
                    ((Monitor)product).setDiagonal(((MonitorDto)dto).getDiagonal());
                }
            }
            case DESKTOP_COMPUTER -> {
                if(((DesktopComputerDto)dto).getFormFactor() != null){
                    ((DesktopComputer)product).setFormFactor(((DesktopComputerDto)dto).getFormFactor());
                }
            }
        }
    }
}
