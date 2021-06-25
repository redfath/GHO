package com.example.gho.Controller;

import com.example.gho.Dao.PatientRepository;
import com.example.gho.Model.Patient;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller

public class PatientController {

    @Autowired
    PatientRepository patientRepository;

    @GetMapping("/Patient")
    public String getPatient(Model model,
                             @RequestParam(defaultValue = "0",required = false)int page,
                             @RequestParam(defaultValue = "5",required = false)int size,
                            @RequestParam(defaultValue = "",required = false) String mc)


    {

        Page<Patient> ListPatient = patientRepository.findAllByNomContains(mc, PageRequest.of(page,size));
        model.addAttribute("patient",ListPatient.getContent());
        model.addAttribute("pages", new int[ListPatient.getTotalPages()]);
        model.addAttribute( "size", size );
        model.addAttribute("currentPage", page);
        model.addAttribute("mc", mc);

        return "Patient";
    }

    @GetMapping("/supprimerPatient")
    public String supprimerPatient(@RequestParam(required = true) Integer id){
        patientRepository.deleteById(id);
        return "redirect:/Patient";
    }

    @GetMapping("/modifierPatient")
    public String modifierPatient(Model model, @RequestParam(required = true) Integer id){
        model.addAttribute("patient",patientRepository.findById(id).get());
        return "ajoutPatient";
    }
    @GetMapping("/ajoutPatient")
    public String ajouterPatient(Model model){
        model.addAttribute("patient", new Patient());
        return "ajoutPatient";
    }

    @PostMapping("/validerFormulaire")
    public String validerFormulaire(@Valid Patient patient, BindingResult bindingResult){
     if (bindingResult.hasErrors()){
         return "ajoutPatient";
     }
        patientRepository.save(patient);
    return "redirect:/Patient";
    }




}
