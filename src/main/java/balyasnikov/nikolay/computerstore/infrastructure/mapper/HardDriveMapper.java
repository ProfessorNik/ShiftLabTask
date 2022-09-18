package balyasnikov.nikolay.computerstore.infrastructure.mapper;

import balyasnikov.nikolay.computerstore.domain.entity.HardDrive;
import balyasnikov.nikolay.computerstore.domain.entity.Monitor;
import balyasnikov.nikolay.computerstore.domain.entity.Product;
import balyasnikov.nikolay.computerstore.infrastructure.dto.HardDriveDto;
import balyasnikov.nikolay.computerstore.infrastructure.dto.MonitorDto;
import balyasnikov.nikolay.computerstore.infrastructure.dto.ProductDto;

public class HardDriveMapper extends ProductMapper{
    @Override
    public void fillFromDto(Product product, ProductDto dto) {
        super.fillFromDto(product, dto);
        ((HardDrive)product).setCapacity(((HardDriveDto)dto).getCapacity());
    }

    @Override
    public void updateFromDto(Product product, ProductDto dto) {
        super.updateFromDto(product, dto);
        if(((HardDriveDto)dto).getCapacity() != null){
            ((HardDrive)product).setCapacity(((HardDriveDto)dto).getCapacity());
        }
    }
}
