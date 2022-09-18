package balyasnikov.nikolay.computerstore.infrastructure.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private String seriesNumber;
    private String manufacturer;
    private BigDecimal cost;
    private Integer quantity;
}
