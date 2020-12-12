package org.example.controller;



import org.example.entities.DentalServicesEntity;
import org.example.services.DentalServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DentalServicesController {

    @Autowired
    private DentalServicesService dentalServicesService;


    @GetMapping("/servicesPrices")
    public ModelAndView getAllPatients(){
        ModelAndView modelAndView = new ModelAndView("services");
        modelAndView.addObject("services", dentalServicesService.getAll());
        return modelAndView;
    }

    @GetMapping("/addService")
    public ModelAndView addService(){
        ModelAndView modelAndView = new ModelAndView("service");
        modelAndView.addObject("dentalService", new DentalServicesEntity());
        return modelAndView;
    }

    @GetMapping("/editService/{id}")
    public ModelAndView editService(@PathVariable Integer id){
        ModelAndView modelAndView = new ModelAndView("service");
        modelAndView.addObject("dentalService", dentalServicesService.getDentalServiceById(id));
        return modelAndView;
    }

    @PostMapping("/saveService")
    public ModelAndView saveService(@ModelAttribute("dentalService") DentalServicesEntity dentalService, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("redirect:/servicesPrices");
        if (bindingResult.hasErrors()){
            modelAndView.addObject("dentalService", dentalService);
            modelAndView.setViewName("service");
        }

        dentalService.setDeleted(false);
        dentalServicesService.saveDentalService(dentalService);

        return modelAndView;
    }

    @GetMapping("/deleteService/{id}")
    public ModelAndView deleteService(@PathVariable Integer id){
        dentalServicesService.deleteDentalService(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/servicesPrices");
        return modelAndView;
    }



}
