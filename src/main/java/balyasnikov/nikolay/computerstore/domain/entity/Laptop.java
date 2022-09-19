package balyasnikov.nikolay.computerstore.domain.entity;

import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "LAPTOP")
public class Laptop extends Product {
    private static List<Integer> availableSizes = List.of(13, 14, 15, 17);
    @Column(name = "size")
    private Integer size;

    public Integer getSize() {
        return size;
    }

    public void setSize(@NonNull Integer size) {
        if (!availableSizes.contains(size)) {
            throw new IllegalArgumentException("Laptop size should be " + availableSizes);
        }
        this.size = size;
    }
}
