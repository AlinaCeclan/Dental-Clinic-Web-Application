package org.example.services;

import org.example.entities.SpecializationEntity;
import org.example.repositories.SpecializationRepository;
import org.example.repositories.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SpecializationsService {

    @Autowired
    private SpecializationRepository specializationRepository;


    public List<SpecializationEntity> getAllSpecializations() {
        return specializationRepository.findAll();
    }

    public SpecializationEntity getSpecializationById(Integer specialization) {
        return specializationRepository.getOne(specialization);
    }

}
