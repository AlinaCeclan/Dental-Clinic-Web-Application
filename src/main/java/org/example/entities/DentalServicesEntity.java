package org.example.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name="dental_services")
public class DentalServicesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dentalServiceId;

    @Size(min = 3, max = 30, message = "Try again")
    private String dentalServiceName;
    @Size(min = 2, max = 10, message = "Try again")
    private String dentalServicePrice;
    private Boolean deleted;

    public Integer getDentalServiceId() {
        return dentalServiceId;
    }

    public void setDentalServiceId(Integer dentalServiceId) {
        this.dentalServiceId = dentalServiceId;
    }

    public String getDentalServiceName() {
        return dentalServiceName;
    }

    public void setDentalServiceName(String dentalServiceName) {
        this.dentalServiceName = dentalServiceName;
    }

    public String getDentalServicePrice() {
        return dentalServicePrice;
    }

    public void setDentalServicePrice(String dentalServicePrice) {
        this.dentalServicePrice = dentalServicePrice;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
