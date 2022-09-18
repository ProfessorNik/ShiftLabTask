package balyasnikov.nikolay.computerstore.domain.entity;

import balyasnikov.nikolay.computerstore.domain.value.ProductType;
import balyasnikov.nikolay.computerstore.infrastructure.dto.ProductDto;
import jakarta.persistence.*;
import lombok.NonNull;

import java.math.BigDecimal;

@Entity
@Table(name = "PRODUCT")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;
    @Column(name = "series_number")
    private String seriesNumber;
    @Column(name = "manufacturer")
    private String manufacturer;
    @Column(name="cost")
    private BigDecimal cost;
    @Column(name="quantity")
    private Integer quantity;

    public static Product createByType(ProductType productType){
        return switch (productType) {
            case LAPTOP -> new Laptop();
            case MONITOR -> new Monitor();
            case HDD -> new HardDrive();
            case DESKTOP_COMPUTER -> new DesktopComputer();
        };
    }

    public void fillFromDto(ProductDto dto){
        setCost(dto.getCost());
        setManufacturer(dto.getManufacturer());
        setSeriesNumber(dto.getSeriesNumber());
        setQuantity(dto.getQuantity());
    }

    public void updateFromDto(ProductDto dto){
        if(dto.getCost() != null){
            setCost(dto.getCost());
        }
        if(dto.getQuantity() != null){
            setQuantity(dto.getQuantity());
        }
        if(dto.getManufacturer() != null){
            setManufacturer(dto.getManufacturer());
        }
        if(dto.getSeriesNumber() != null){
            setSeriesNumber(dto.getSeriesNumber());
        }
    }

    public long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    public String getSeriesNumber() {
        return seriesNumber;
    }

    public void setSeriesNumber(@NonNull String seriesNumber) {
        this.seriesNumber = seriesNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(@NonNull String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(@NonNull BigDecimal cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(@NonNull Integer quantity) {
        this.quantity = quantity;
    }
}
