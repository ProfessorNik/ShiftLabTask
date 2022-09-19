package balyasnikov.nikolay.computerstore.domain.entity;

import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "HDD")
public class HardDrive extends Product {
    private Integer capacity;

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(@NonNull Integer capacity) {
        if (capacity.compareTo(0) <= 0) {
            throw new IllegalArgumentException("Capacity should be greater then zero");
        }
        this.capacity = capacity;
    }
}
