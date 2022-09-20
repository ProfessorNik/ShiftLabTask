package balyasnikov.nikolay.computerstore.domain.entity;

import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "DESKTOP_COMPUTER")
public class DesktopComputer extends Product {
    private static List<String> availableFormFactors = List.of("desktop", "monoblock", "nettop");
    @Column(name = "form_factor")
    private String formFactor;

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(@NonNull String formFactor) {
        if(!availableFormFactors.contains(formFactor.toLowerCase())){
            throw new IllegalArgumentException("Form factor of the computer can be one of: " + availableFormFactors);
        }
        this.formFactor = formFactor.toLowerCase();
    }
}
