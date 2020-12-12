package org.example.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "specialization")
public class SpecializationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer specializationId;

    private String specializationName;


    @ManyToMany(mappedBy = "specializations")
    private List<UserDetails> userDetails;



    public Integer getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(Integer specializationId) {
        this.specializationId = specializationId;
    }

    public String getSpecializationName() {
        return specializationName;
    }

    public void setSpecializationName(String specializationName) {
        this.specializationName = specializationName;
    }

    public List<UserDetails> getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(List<UserDetails> userDetails) {
        this.userDetails = userDetails;
    }
}
