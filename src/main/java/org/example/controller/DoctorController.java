package org.example.controller;
import org.example.entities.UserDetails;
import org.example.services.AppointmentService;
import org.example.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DoctorController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/justDoctor")
    public ModelAndView justDoctor(){
        ModelAndView modelAndView = new ModelAndView("patient");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserDetails userDetails = userDetailsService.getIdUserByUsername(currentPrincipalName);
        modelAndView.addObject("doctor", userDetailsService.getUser(currentPrincipalName));
        modelAndView.addObject("patients", userDetailsService.getAllPatients());
        return modelAndView;
    }


    @GetMapping("/doctors")
    public ModelAndView getAllDoctors(){
    ModelAndView modelAndView = new ModelAndView("doctors");
    modelAndView.addObject("doctors", userDetailsService.getAllDoctors());
    return modelAndView;
    }

    @GetMapping("/viewAppointments/{id}")
    public ModelAndView viewDoctorsAppointments(@PathVariable Integer id){
        ModelAndView modelAndView = new ModelAndView("appointments");
        modelAndView.addObject("appointments", appointmentService.getAllDoctorsAppointments(id) );
        return modelAndView;
    }





}
