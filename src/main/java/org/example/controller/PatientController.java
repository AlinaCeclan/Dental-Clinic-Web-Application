package org.example.controller;



import org.example.entities.UserDetails;
import org.example.services.AppointmentService;
import org.example.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


import javax.validation.Valid;

@Controller
public class PatientController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AppointmentService appointmentService;


    @GetMapping("/home")
    public ModelAndView frontpage(){
        ModelAndView modelAndView = new ModelAndView("frontpage");
        return modelAndView;
    }

    @GetMapping("/patients")
    public ModelAndView getAllPatients(){
        ModelAndView modelAndView = new ModelAndView("patient");
        modelAndView.addObject("patients", userDetailsService.getAllPatients());
        return modelAndView;
    }

    @GetMapping("/viewPatientHistory/{id}")
    public ModelAndView viewPatientHistory(@PathVariable Integer id){
        ModelAndView modelAndView = new ModelAndView("patienthistory");
        modelAndView.addObject("appointments", appointmentService.getAllPatientsAppointments(id));
        return modelAndView;
    }


    @GetMapping("/justPatient")
    public ModelAndView justPatient(){
        ModelAndView modelAndView = new ModelAndView("justpatient");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserDetails userDetails = userDetailsService.getIdUserByUsername(currentPrincipalName);
        modelAndView.addObject("appointments", appointmentService.getAllPatientAppointmentsByUsername(currentPrincipalName));
        modelAndView.addObject("patient", userDetailsService.getUser(currentPrincipalName));
        return modelAndView;
    }




}
