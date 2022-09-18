package balyasnikov.nikolay.computerstore.domain.entity;

import balyasnikov.nikolay.computerstore.infrastructure.dto.LaptopDto;
import balyasnikov.nikolay.computerstore.infrastructure.dto.ProductDto;
import javax.persistence.*;
import lombok.NonNull;

@Entity
@Table(name="LAPTOP")
public class Laptop extends Product{
    @Column(name="size")
    private Integer size;

    public Integer getSize() {
        return size;
    }

    public void setSize(@NonNull Integer size) {
        this.size = size;
    }
}
