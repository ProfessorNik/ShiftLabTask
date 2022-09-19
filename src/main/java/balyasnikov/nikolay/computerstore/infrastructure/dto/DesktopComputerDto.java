package balyasnikov.nikolay.computerstore.infrastructure.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DesktopComputerDto extends ProductDto {
    private String formFactor;
}
