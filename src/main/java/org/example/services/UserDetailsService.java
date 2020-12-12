package org.example.services;

import org.example.entities.AppointmentEntity;
import org.example.entities.User;
import org.example.entities.UserDetails;
import org.example.repositories.UserDetailsRepository;
import org.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service//este o forma speciala a adnotarii @Component, marcheaza clasele care contin logica de business
public class UserDetailsService {

    @Autowired//instantiem o calasa in alta cu @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private UserRepository userRepository;


    public List<User> getAllPatients() {
        return userRepository.getUsersList("PATIENT");
    }

    public List<User> getAllDoctors() {
        return userRepository.getUsersList("DOCTOR");
    }

    public UserDetails getUser(Integer id) {
        return userDetailsRepository.getOne(id);
    }

    public UserDetails getIdUserByUsername(String username) {
        return userDetailsRepository.getIdByUsername(username);
    }

    public void delete(String id) {
        User user = userRepository.getOne(id);
        user.setEnabled(false);
        user.setDeleted(true);
        userRepository.save(user);
    }

    public UserDetails getUser(String username) {
        return userDetailsRepository.getIdByUsername(username);
    }

}
