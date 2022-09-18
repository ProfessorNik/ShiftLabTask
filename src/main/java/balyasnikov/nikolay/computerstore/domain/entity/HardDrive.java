package balyasnikov.nikolay.computerstore.domain.entity;

import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "HDD")
public class HardDrive extends Product{
    private Integer capacity;

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(@NonNull Integer capacity) {
        this.capacity = capacity;
    }
}
