package balyasnikov.nikolay.computerstore.infrastructure.mapper;

import balyasnikov.nikolay.computerstore.domain.entity.DesktopComputer;
import balyasnikov.nikolay.computerstore.domain.entity.HardDrive;
import balyasnikov.nikolay.computerstore.domain.entity.Product;
import balyasnikov.nikolay.computerstore.infrastructure.dto.DesktopComputerDto;
import balyasnikov.nikolay.computerstore.infrastructure.dto.HardDriveDto;
import balyasnikov.nikolay.computerstore.infrastructure.dto.ProductDto;

public class DesktopComputerMapper extends ProductMapper{
    @Override
    public void fillFromDto(Product product, ProductDto dto) {
        super.fillFromDto(product, dto);
        ((DesktopComputer)product).setFormFactor(((DesktopComputerDto)dto).getFormFactor());
    }

    @Override
    public void updateFromDto(Product product, ProductDto dto) {
        super.updateFromDto(product, dto);
        if(((DesktopComputerDto)dto).getFormFactor() != null){
            ((DesktopComputer)product).setFormFactor(((DesktopComputerDto)dto).getFormFactor());
        }
    }
}
