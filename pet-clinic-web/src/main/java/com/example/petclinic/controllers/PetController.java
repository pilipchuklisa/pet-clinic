package com.example.petclinic.controllers;

import com.example.petclinic.model.Owner;
import com.example.petclinic.model.Pet;
import com.example.petclinic.model.PetType;
import com.example.petclinic.services.OwnerService;
import com.example.petclinic.services.PetService;
import com.example.petclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@RequestMapping("/owners/{ownerId}/pets")
@Controller
public class PetController {

    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/new")
    public ModelAndView showCreatePetForm() {
        ModelAndView mav = new ModelAndView("pets/createOrUpdatePetForm");
        mav.addObject("pet", new Pet());
        return mav;
    }

    @PostMapping("/new")
    public String createPet(@ModelAttribute("pet") Pet pet, @ModelAttribute("owner") Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return "pets/createOrUpdatePetForm";
        }

        pet.setOwner(owner);
        petService.save(pet);
        return "redirect:/owners/" + pet.getOwner().getId();
    }

    @GetMapping("/{petId}/edit")
    public ModelAndView showUpdatePetForm(@PathVariable("petId") Long id) {
        ModelAndView mav = new ModelAndView("pets/createOrUpdatePetForm");
        mav.addObject("pet", petService.findById(id));
        return mav;
    }

    @PostMapping("/{petId}/edit")
    public String createPet(@PathVariable("petId") Long id, @ModelAttribute("pet") Pet pet,
                            @ModelAttribute("owner") Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return "pets/createOrUpdatePetForm";
        }

        pet.setId(id);
        pet.setOwner(owner);
        petService.save(pet);
        return "redirect:/owners/" + pet.getOwner().getId();
    }
}
