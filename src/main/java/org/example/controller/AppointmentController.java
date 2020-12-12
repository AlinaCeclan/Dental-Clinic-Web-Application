package org.example.controller;



import org.example.entities.AppointmentEntity;
import org.example.entities.DentalServicesEntity;
import org.example.entities.UserDetails;
import org.example.models.AppointmentModel;
import org.example.services.AppointmentService;
import org.example.services.DentalServicesService;
import org.example.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DentalServicesService dentalServicesService;



    @GetMapping("/addAppointment")
    public ModelAndView addAppointment(){
        ModelAndView modelAndView = new ModelAndView("appointment");
        modelAndView.addObject("appointments", new AppointmentModel());
        modelAndView.addObject("patients", userDetailsService.getAllPatients());
        modelAndView.addObject("doctors", userDetailsService.getAllDoctors());
        modelAndView.addObject("dentalServices",dentalServicesService.getAll());
        return modelAndView;
    }

    @PostMapping("/saveAppointment")
    public ModelAndView saveNewAppointment(@ModelAttribute("appointments") AppointmentModel appointmentModel){
        ModelAndView modelAndView = new ModelAndView("redirect:/doctors");
        UserDetails patient = userDetailsService.getUser(appointmentModel.getPatientId());
        UserDetails doctor = userDetailsService.getUser(appointmentModel.getDoctorId());
        AppointmentEntity appointmentEntity = new AppointmentEntity();
        appointmentEntity.setUserDetailsDoctor(doctor);
        appointmentEntity.setUserDetailsPatient(patient);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try{
            Date date = simpleDateFormat.parse(appointmentModel.getAppointmentDateTime());
            Timestamp timestamp = new Timestamp(date.getTime());
            appointmentEntity.setAppointmentDateTime(timestamp);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        appointmentEntity.setDeleted(false);
        appointmentEntity.setCanceled(false);

        DentalServicesEntity dentalService = dentalServicesService.getDentalServiceById(appointmentModel.getDentalServiceId());
        appointmentEntity.setDentalServicesEntity(dentalService);

        appointmentService.saveAppointment(appointmentEntity);
        return modelAndView;
    }

    @GetMapping("/deleteAppointment/{id}")
    public ModelAndView deleteDoctorAppointment(@PathVariable Integer id){
        appointmentService.deleteAppointment(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/viewAppointments/{id}");
        return modelAndView;
    }

    @GetMapping("/cancelAppointment/{id}")
    public ModelAndView cancelAppointment(@PathVariable Integer id){
        appointmentService.cancelAppointment(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/justPatient");
        return modelAndView;
    }


}
