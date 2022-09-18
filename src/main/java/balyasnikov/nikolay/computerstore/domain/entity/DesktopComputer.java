package balyasnikov.nikolay.computerstore.domain.entity;

import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "DESKTOP_COMPUTER")
public class DesktopComputer extends Product{
    @Column(name = "form_factor")
    private String formFactor;


    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(@NonNull String formFactor) {
        this.formFactor = formFactor;
    }
}
