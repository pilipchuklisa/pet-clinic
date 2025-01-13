package com.example.petclinic.controllers;

import com.example.petclinic.model.Owner;
import com.example.petclinic.model.Pet;
import com.example.petclinic.model.Visit;
import com.example.petclinic.services.OwnerService;
import com.example.petclinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@RequestMapping("/owners/{ownerId}/pets/{petId}/visits")
@Controller
public class VisitController {

    private final OwnerService ownerService;
    private final VisitService visitService;

    public VisitController(OwnerService ownerService, VisitService visitService) {
        this.ownerService = ownerService;
        this.visitService = visitService;
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @ModelAttribute("pet")
    public Pet findPet(@PathVariable("ownerId") Long ownerId,
                       @PathVariable(name = "petId", required = false) Long petId) {

        if (petId == null) {
            return new Pet();
        }
        Owner owner = ownerService.findById(ownerId);

        return owner.getPets().stream()
                .filter(p -> Objects.equals(p.getId(), petId))
                .findFirst()
                .get();
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/new")
    public ModelAndView showCreateVisitForm() {
        ModelAndView mav = new ModelAndView("pets/createOrUpdateVisitForm");
        mav.addObject("visit", new Visit());
        return mav;
    }

    @PostMapping("/new")
    public String createVisit(@ModelAttribute("visit") Visit visit, @ModelAttribute("pet") Pet pet,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "pets/createOrUpdatePetForm";
        }

        visit.setPet(pet);
        visitService.save(visit);
        return "redirect:/owners/" + visit.getPet().getOwner().getId();
    }

    @GetMapping("/{visitId}/edit")
    public ModelAndView showUpdateVisitForm(@PathVariable("visitId") Long id) {
        ModelAndView mav = new ModelAndView("pets/createOrUpdateVisitForm");
        mav.addObject("visit", visitService.findById(id));
        return mav;
    }

    @PostMapping("/{visitId}/edit")
    public String updateVisit(@PathVariable("visitId") Long id, @ModelAttribute("visit") Visit visit,
                              @ModelAttribute("pet") Pet pet, BindingResult result) {
        if (result.hasErrors()) {
            return "pets/createOrUpdatePetForm";
        }

        visit.setId(id);
        visit.setPet(pet);
        visitService.save(visit);
        return "redirect:/owners/" + visit.getPet().getOwner().getId();
    }
}
