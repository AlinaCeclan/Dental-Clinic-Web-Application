package org.example.models;


public class AppointmentModel {

    private Integer patientId;
    private Integer doctorId;
    private String appointmentDateTime;
    private Integer dentalServiceId;

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(String appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    public Integer getDentalServiceId() {
        return dentalServiceId;
    }

    public void setDentalServiceId(Integer dentalServiceId) {
        this.dentalServiceId = dentalServiceId;
    }
}
