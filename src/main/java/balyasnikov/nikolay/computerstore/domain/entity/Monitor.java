package balyasnikov.nikolay.computerstore.domain.entity;

import balyasnikov.nikolay.computerstore.infrastructure.dto.MonitorDto;
import balyasnikov.nikolay.computerstore.infrastructure.dto.ProductDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NonNull;

import java.util.List;

@Entity
@Table(name="MONITOR")
public class Monitor extends Product {
    private static List<Double> availableDiagonals = List.of(13.0, 14.0, 15.0 ,17.0);
    @Column(name = "diagonal")
    private Double diagonal;

    @Override
    public void fillFromDto(ProductDto dto) {
        super.fillFromDto(dto);
        setDiagonal(((MonitorDto)dto).getDiagonal());
    }

    @Override
    public void updateFromDto(ProductDto dto) {
        super.updateFromDto(dto);
        if(((MonitorDto)dto).getDiagonal() != null){
            setDiagonal(((MonitorDto)dto).getDiagonal());
        }
    }

    public Double getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(@NonNull Double diagonal) {
        if(!availableDiagonals.contains(diagonal))
            throw new IllegalArgumentException("Diagonal should be " + availableDiagonals);
        this.diagonal = diagonal;
    }
}
