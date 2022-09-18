package balyasnikov.nikolay.computerstore.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
