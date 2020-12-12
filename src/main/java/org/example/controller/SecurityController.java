package org.example.controller;

import org.example.entities.*;
import org.example.models.RegisterModel;
import org.example.repositories.AuthoritiesRepository;
import org.example.repositories.UserDetailsRepository;
import org.example.repositories.UserRepository;
import org.example.services.SpecializationsService;
import org.example.services.UserDetailsService;
import org.example.services.UserTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class SecurityController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthoritiesRepository authoritiesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private UserTypesService userTypesService;

    @Autowired
    private SpecializationsService specializationsService;

    @Autowired
    private UserDetailsService userDetailsService;


    @GetMapping("/403") //url-ul pt login error
    public ModelAndView showErrorLogin(){
        ModelAndView modelAndView = new ModelAndView("f403");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView register(){
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("registerModel", new RegisterModel());
        modelAndView.addObject("userTypes", userTypesService.getAllUserTypes());
        modelAndView.addObject("doctors", userDetailsService.getAllDoctors());
        modelAndView.addObject("specializations", specializationsService.getAllSpecializations());
        return modelAndView;
    }

    @GetMapping("/editUser/{username}")
    public ModelAndView editUser(@PathVariable String username){
        ModelAndView modelAndView = new ModelAndView("register");
        User user = userRepository.findByUsername(username);
        RegisterModel registerModel = new RegisterModel();
        registerModel.setUsername(user.getUsername());

        if(null != user.getUserDetails()){
            registerModel.setFirstName(user.getUserDetails().getFirstName());
            registerModel.setLastName(user.getUserDetails().getLastName());
            registerModel.setEmail(user.getUserDetails().getEmail());
            registerModel.setPhoneNumber(user.getUserDetails().getPhoneNumber());
            registerModel.setAge(user.getUserDetails().getAge());

            registerModel.setUserTypesId(user.getUserDetails().getUserTypes().getUserTypeId());

            for( SpecializationEntity specialization : user.getUserDetails().getSpecializations() ){

                registerModel.getSpecializations().add(specialization.getSpecializationId());
            }

        }
        modelAndView.addObject("userTypes", userTypesService.getAllUserTypes());
        modelAndView.addObject("specializations", specializationsService.getAllSpecializations());
        modelAndView.addObject("registerModel", registerModel);
        return modelAndView;
    }

    @PostMapping("/register-user")
    public ModelAndView registerUser(@ModelAttribute("registerModel") RegisterModel registerModel){
        Optional<User> userEntityOptional = userRepository.findById(registerModel.getUsername());
        User user = null;
        if(userEntityOptional.isPresent()){
            user = userEntityOptional.get();
        } else {
            user = new User();
        }
        user.setUsername(registerModel.getUsername());
        user.setPassword(passwordEncoder.encode(registerModel.getPassword()));
        user.setEnabled(true);
        user.setDeleted(false);

        UserDetails userDetails = null;
        if(userEntityOptional.isPresent()){
            userDetails = user.getUserDetails();

        } else {
            userDetails = new UserDetails();
        }
        userDetails.setFirstName(registerModel.getFirstName());
        userDetails.setLastName(registerModel.getLastName());
        userDetails.setEmail(registerModel.getEmail());
        userDetails.setPhoneNumber(registerModel.getPhoneNumber());
        userDetails.setAge(registerModel.getAge());
        List<SpecializationEntity> specializationEntityList = new ArrayList<SpecializationEntity>();
        for(Integer specializationId : registerModel.getSpecializations()){
            specializationEntityList.add(specializationsService.getSpecializationById(specializationId));
        }
        userDetails.setSpecializations(specializationEntityList);

        UserTypes userTypes = userTypesService.getUserTypeById(registerModel.getUserTypesId());
        userDetails.setUserTypes(userTypes);

        //daca este un edit de user, atunci nu schimbam rolurile, nu permitem asta
        if(!userEntityOptional.isPresent()) {
            Authorities authorities = new Authorities(); //daca nu facem edit
            authorities.setUsername(registerModel.getUsername());
            authorities.setAuthority("ROLE_" + registerModel.getUserTypesId());
            authoritiesRepository.save(authorities);
        }
        userRepository.save(user);
        userDetails.setUser(user);
        userDetailsRepository.save(userDetails);
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        return modelAndView;
    }

    @GetMapping("/deleteUser/{id}")
    public ModelAndView deleteUser(@PathVariable String id){
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        userDetailsService.delete(id);
        return modelAndView;
    }

}
