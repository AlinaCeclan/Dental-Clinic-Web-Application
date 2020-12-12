package org.example.repositories;

import org.example.entities.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//marcheaza clasele care acceseaza direct baza de date
public interface AppointmentRepository  extends JpaRepository<AppointmentEntity, Integer> {

    @Query(" FROM AppointmentEntity where userDetailsDoctor.userDetailsId = ?1 and deleted = false order by appointmentDateTime")
    List<AppointmentEntity> getAllDoctorsAppointments(Integer id);

    @Query(" FROM AppointmentEntity where userDetailsPatient.userDetailsId = ?1 and deleted = false order by appointmentDateTime")
    List<AppointmentEntity> getAllPatientsAppointments(Integer id);

    @Query(" FROM AppointmentEntity where userDetailsPatient.user.username = ?1 and deleted = false order by appointmentDateTime")
    List<AppointmentEntity> getAllPatientsAppointmentsByUsername(String username);
}
