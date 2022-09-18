package balyasnikov.nikolay.computerstore.domain.entity;

import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="MONITOR")
public class Monitor extends Product {
    private static List<Double> availableDiagonals = List.of(13.0, 14.0, 15.0 ,17.0);
    @Column(name = "diagonal")
    private Double diagonal;

    public Double getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(@NonNull Double diagonal) {
        if(!availableDiagonals.contains(diagonal))
            throw new IllegalArgumentException("Diagonal should be " + availableDiagonals);
        this.diagonal = diagonal;
    }
}
