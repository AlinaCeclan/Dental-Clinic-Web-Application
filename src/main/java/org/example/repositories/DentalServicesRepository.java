package org.example.repositories;

import org.example.entities.DentalServicesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DentalServicesRepository extends JpaRepository<DentalServicesEntity, Integer> {

    List<DentalServicesEntity> getAllByDeletedFalse();

}
