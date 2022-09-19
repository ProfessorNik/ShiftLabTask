package balyasnikov.nikolay.computerstore.infrastructure.mapper;

import balyasnikov.nikolay.computerstore.domain.entity.Laptop;
import balyasnikov.nikolay.computerstore.domain.entity.Product;
import balyasnikov.nikolay.computerstore.infrastructure.dto.LaptopDto;
import balyasnikov.nikolay.computerstore.infrastructure.dto.ProductDto;

public class LaptopMapper extends ProductMapper {
    @Override
    public void fillFromDto(Product product, ProductDto dto) {
        super.fillFromDto(product, dto);
        ((Laptop) product).setSize(((LaptopDto) dto).getSize());
    }

    @Override
    public void updateFromDto(Product product, ProductDto dto) {
        super.updateFromDto(product, dto);
        if (((LaptopDto) dto).getSize() != null) {
            ((Laptop) product).setSize(((LaptopDto) dto).getSize());
        }
    }
}
