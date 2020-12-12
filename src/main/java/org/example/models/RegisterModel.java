package org.example.models;
import java.util.ArrayList;
import java.util.List;

public class RegisterModel {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Integer age;
    private Integer userTypesId;
    private String authority;
    private List<Integer> specializations;


    public RegisterModel(){
        this.specializations = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Integer getUserTypesId() {
        return userTypesId;
    }

    public void setUserTypesId(Integer userTypesId) {
        this.userTypesId = userTypesId;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public List<Integer> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<Integer> specializations) {
        this.specializations = specializations;
    }

}
