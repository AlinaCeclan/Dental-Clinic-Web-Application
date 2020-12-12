package org.example.services;

import org.example.entities.DentalServicesEntity;
import org.example.repositories.DentalServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DentalServicesService {

    @Autowired
    private DentalServicesRepository dentalServicesRepository;


    public List<DentalServicesEntity> getAll() {
        return dentalServicesRepository.getAllByDeletedFalse();
    }

    public DentalServicesEntity getDentalServiceById(Integer dentalServiceId) {
        return dentalServicesRepository.getOne(dentalServiceId);
    }

    public void saveDentalService(DentalServicesEntity dentalServicesEntity) {
        dentalServicesRepository.save(dentalServicesEntity);
    }

    public void deleteDentalService(Integer id) {
        DentalServicesEntity dentalServicesEntity = dentalServicesRepository.getOne(id);
        dentalServicesEntity.setDeleted(true);
        dentalServicesRepository.save(dentalServicesEntity);
    }
}
