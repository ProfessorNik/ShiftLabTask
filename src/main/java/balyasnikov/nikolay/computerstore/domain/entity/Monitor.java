package balyasnikov.nikolay.computerstore.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NonNull;

@Entity
@Table(name="MONITOR")
public class Monitor extends Product {
    @Column(name = "diagonal")
    private Double diagonal;

    public Double getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(@NonNull Double diagonal) {
        this.diagonal = diagonal;
    }
}
