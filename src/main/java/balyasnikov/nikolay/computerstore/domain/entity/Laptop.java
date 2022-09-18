package balyasnikov.nikolay.computerstore.domain.entity;

import balyasnikov.nikolay.computerstore.infrastructure.dto.LaptopDto;
import balyasnikov.nikolay.computerstore.infrastructure.dto.ProductDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NonNull;

@Entity
@Table(name="LAPTOP")
public class Laptop extends Product{
    @Column(name="size")
    private Integer size;

    @Override
    public void fillFromDto(ProductDto dto) {
        super.fillFromDto(dto);
        setSize(((LaptopDto)dto).getSize());
    }

    @Override
    public void updateFromDto(ProductDto dto) {
        super.updateFromDto(dto);
        if(((LaptopDto)dto).getSize() != null){
            setSize(((LaptopDto)dto).getSize());
        }
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(@NonNull Integer size) {
        this.size = size;
    }
}
