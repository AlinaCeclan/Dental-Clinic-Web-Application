package org.example.repositories;

import org.example.entities.SpecializationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SpecializationRepository extends JpaRepository<SpecializationEntity,Integer> {

List<SpecializationEntity> findAllBySpecializationId(List<Integer> specializations);

}
