package org.example.entities;



import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;



@Entity //aceasta clasa este mapata la o tabela in baza de date
@Table(name="appointment")
public class AppointmentEntity {

    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appointmentId;


    private Timestamp appointmentDateTime;


    @ManyToOne
    @JoinColumn(name = "userDetailsPatient")
    private UserDetails userDetailsPatient;

    @ManyToOne
    @JoinColumn(name = "userDetailsDoctor")
    private UserDetails userDetailsDoctor;

    @OneToOne
    @JoinColumn(name="dentalServiceId")
    private DentalServicesEntity DentalServicesEntity;

    private Boolean deleted;

    private Boolean canceled;


    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Timestamp getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(Timestamp appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    public UserDetails getUserDetailsPatient() {
        return userDetailsPatient;
    }

    public void setUserDetailsPatient(UserDetails userDetailsPatient) {
        this.userDetailsPatient = userDetailsPatient;
    }

    public UserDetails getUserDetailsDoctor() {
        return userDetailsDoctor;
    }

    public void setUserDetailsDoctor(UserDetails userDetailsDoctor) {
        this.userDetailsDoctor = userDetailsDoctor;
    }

    public org.example.entities.DentalServicesEntity getDentalServicesEntity() {
        return DentalServicesEntity;
    }

    public void setDentalServicesEntity(org.example.entities.DentalServicesEntity dentalServicesEntity) {
        DentalServicesEntity = dentalServicesEntity;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getCanceled() {
        return canceled;
    }

    public void setCanceled(Boolean canceled) {
        this.canceled = canceled;
    }
}
