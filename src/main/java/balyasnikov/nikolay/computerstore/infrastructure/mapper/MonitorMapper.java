package balyasnikov.nikolay.computerstore.infrastructure.mapper;

import balyasnikov.nikolay.computerstore.domain.entity.Monitor;
import balyasnikov.nikolay.computerstore.domain.entity.Product;
import balyasnikov.nikolay.computerstore.infrastructure.dto.MonitorDto;
import balyasnikov.nikolay.computerstore.infrastructure.dto.ProductDto;

public class MonitorMapper extends ProductMapper {

    @Override
    public void fillFromDto(Product product, ProductDto dto) {
        super.fillFromDto(product, dto);
        ((Monitor) product).setDiagonal(((MonitorDto) dto).getDiagonal());
    }

    @Override
    public void updateFromDto(Product product, ProductDto dto) {
        super.updateFromDto(product, dto);
        if (((MonitorDto) dto).getDiagonal() != null) {
            ((Monitor) product).setDiagonal(((MonitorDto) dto).getDiagonal());
        }
    }
}
