package org.example.entities;
import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="user_details")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userDetailsId;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private Integer age;

    @OneToMany(mappedBy = "userDetailsPatient")
    private List<AppointmentEntity> appointmentsPatient;

    @OneToMany(mappedBy = "userDetailsDoctor")
    private List<AppointmentEntity> appointmentsDoctor;

    @ManyToMany
    @JoinTable(
            name="userDetails_specializations",
            joinColumns = {@JoinColumn(name = "userDetailsId")},
            inverseJoinColumns = {@JoinColumn(name = "specializationId")}

    )
    private List<SpecializationEntity> specializations;

    @OneToOne
    @JoinColumn(name="userTypeId")
    private UserTypes userTypes;

    @OneToOne
    @JoinColumn(name="username")
    private User user;

    public Integer getUserDetailsId() {
        return userDetailsId;
    }

    public void setUserDetailsId(Integer userDetailsId) {
        this.userDetailsId = userDetailsId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<AppointmentEntity> getAppointmentsPatient() {
        return appointmentsPatient;
    }

    public void setAppointmentsPatient(List<AppointmentEntity> appointmentsPatient) {
        this.appointmentsPatient = appointmentsPatient;
    }

    public List<AppointmentEntity> getAppointmentsDoctor() {
        return appointmentsDoctor;
    }

    public void setAppointmentsDoctor(List<AppointmentEntity> appointmentsDoctor) {
        this.appointmentsDoctor = appointmentsDoctor;
    }

    public List<SpecializationEntity> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<SpecializationEntity> specializations) {
        this.specializations = specializations;
    }

    public UserTypes getUserTypes() {
        return userTypes;
    }

    public void setUserTypes(UserTypes userTypes) {
        this.userTypes = userTypes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
