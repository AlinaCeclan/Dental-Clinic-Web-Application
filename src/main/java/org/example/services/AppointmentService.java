package org.example.services;

import org.example.entities.AppointmentEntity;
import org.example.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;


    public void saveAppointment(AppointmentEntity appointmentEntity) {
        appointmentRepository.save(appointmentEntity);
    }

    public void deleteAppointment(Integer id) {
        AppointmentEntity appointmentEntity = appointmentRepository.getOne(id);
        appointmentEntity.setDeleted(true);
        appointmentEntity.setCanceled(true);
        appointmentRepository.save(appointmentEntity);
    }

    public List<AppointmentEntity> getAllDoctorsAppointments(Integer id) {
        return appointmentRepository.getAllDoctorsAppointments(id);
    }

    public List<AppointmentEntity> getAllPatientsAppointments(Integer id) {
        return appointmentRepository.getAllPatientsAppointments(id);
    }

    public List<AppointmentEntity> getAllPatientAppointmentsByUsername(String username) {
        return appointmentRepository.getAllPatientsAppointmentsByUsername(username);
    }

    public void cancelAppointment(Integer id) {
        AppointmentEntity appointmentEntity = appointmentRepository.getOne(id);
        appointmentEntity.setCanceled(true);
        appointmentRepository.save(appointmentEntity);
    }
}
