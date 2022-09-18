package balyasnikov.nikolay.computerstore.domain.entity;

import balyasnikov.nikolay.computerstore.infrastructure.dto.HardDriveDto;
import balyasnikov.nikolay.computerstore.infrastructure.dto.ProductDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NonNull;

@Entity
@Table(name = "HDD")
public class HardDrive extends Product{
    private Integer capacity;

    @Override
    public void fillFromDto(ProductDto dto) {
        super.fillFromDto(dto);
        setCapacity(((HardDriveDto)dto).getCapacity());
    }

    @Override
    public void updateFromDto(ProductDto dto) {
        super.updateFromDto(dto);
        if(((HardDriveDto)dto).getCapacity() != null){
            setCapacity(((HardDriveDto)dto).getCapacity());
        }
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(@NonNull Integer capacity) {
        this.capacity = capacity;
    }
}
