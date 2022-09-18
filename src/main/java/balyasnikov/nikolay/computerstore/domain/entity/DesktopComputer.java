package balyasnikov.nikolay.computerstore.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "DESKTOP_COMPUTER")
public class DesktopComputer extends Product{
    @Column(name = "form_factor")
    private String formFactor;

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }
}
