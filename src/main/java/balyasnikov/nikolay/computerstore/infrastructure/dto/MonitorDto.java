package balyasnikov.nikolay.computerstore.infrastructure.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonitorDto extends ProductDto {
    private Double diagonal;
}
