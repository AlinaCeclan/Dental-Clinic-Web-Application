package org.example.entities;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column( length = 20)
    private String username;
    private String password;
    private Boolean enabled;
    private Boolean deleted;

    @OneToOne(mappedBy = "user")
    private UserDetails userDetails;

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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
}
