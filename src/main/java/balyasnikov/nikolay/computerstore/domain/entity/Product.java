package balyasnikov.nikolay.computerstore.domain.entity;

import balyasnikov.nikolay.computerstore.domain.value.ProductType;
import lombok.NonNull;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PRODUCT")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "series_number")
    private String seriesNumber;
    @Column(name = "manufacturer")
    private String manufacturer;
    @Column(name = "cost")
    private BigDecimal cost;
    @Column(name = "quantity")
    private Integer quantity;

    public static Product createByType(ProductType productType) {
        return switch (productType) {
            case LAPTOP -> new Laptop();
            case MONITOR -> new Monitor();
            case HDD -> new HardDrive();
            case DESKTOP_COMPUTER -> new DesktopComputer();
        };
    }

    public Long getId() {
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
        if (cost.compareTo(new BigDecimal(0)) < 0) {
            throw new IllegalArgumentException("Cost must by more or equal then zero");
        }
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(@NonNull Integer quantity) {
        if (quantity.compareTo(0) < 0) {
            throw new IllegalArgumentException("Quantity must by more or equal to zero");
        }
        this.quantity = quantity;
    }
}
