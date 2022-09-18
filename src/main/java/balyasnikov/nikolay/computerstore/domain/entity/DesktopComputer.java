package balyasnikov.nikolay.computerstore.domain.entity;

import balyasnikov.nikolay.computerstore.infrastructure.dto.DesktopComputerDto;
import balyasnikov.nikolay.computerstore.infrastructure.dto.ProductDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NonNull;

@Entity
@Table(name = "DESKTOP_COMPUTER")
public class DesktopComputer extends Product{
    @Column(name = "form_factor")
    private String formFactor;

    @Override
    public void fillFromDto(ProductDto dto) {
        super.fillFromDto(dto);
        setFormFactor(((DesktopComputerDto)dto).getFormFactor());
    }

    @Override
    public void updateFromDto(ProductDto dto) {
        super.updateFromDto(dto);
        if(((DesktopComputerDto)dto).getFormFactor() != null){
            setFormFactor(((DesktopComputerDto)dto).getFormFactor());
        }
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(@NonNull String formFactor) {
        this.formFactor = formFactor;
    }
}
