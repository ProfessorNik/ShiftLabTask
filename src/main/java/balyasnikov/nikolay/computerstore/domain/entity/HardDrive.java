package balyasnikov.nikolay.computerstore.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NonNull;

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
