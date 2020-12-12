package org.example.services;

import org.example.entities.UserTypes;
import org.example.repositories.UserTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserTypesService {

    @Autowired
    private UserTypesRepository userTypesRepository;


    public List<UserTypes> getAllUserTypes() {
        return userTypesRepository.findAll();
    }

    public UserTypes getUserTypeById(Integer userTypeId) {
        return userTypesRepository.getOne(userTypeId);
    }
}
