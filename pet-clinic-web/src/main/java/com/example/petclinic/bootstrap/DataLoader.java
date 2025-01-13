package com.example.petclinic.bootstrap;

import com.example.petclinic.model.*;
import com.example.petclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetService petService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetService petService,
                      PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petService = petService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType petType1 = new PetType();
        petType1.setName("pet type 1");
        petTypeService.save(petType1);

        PetType petType2 = new PetType();
        petType2.setName("pet type 2");
        petTypeService.save(petType2);

        Pet pet1 = new Pet();
        pet1.setName("pet 1");
        pet1.setBirthDate(LocalDate.of(2020, 12, 12));
        pet1.setType(petType1);

        Pet pet2 = new Pet();
        pet2.setName("pet 2");
        pet2.setBirthDate(LocalDate.of(2021, 12, 12));
        pet2.setType(petType2);

        Owner owner1 = new Owner();
        owner1.setAddress("address for owner 1");
        owner1.setCity("city for owner 1");
        owner1.setTelephone("12345678");
        owner1.setFirstName("Eva");
        owner1.setLastName("Stone");
        owner1.setPets(new HashSet<>(List.of(pet1)));
        ownerService.save(owner1);

        pet1.setOwner(owner1);
        petService.save(pet1);

        Owner owner2 = new Owner();
        owner2.setAddress("address for owner 2");
        owner2.setCity("city for owner 2");
        owner2.setTelephone("23456789");
        owner2.setFirstName("Elena");
        owner2.setLastName("Quartz");
        owner2.setPets(new HashSet<>(List.of(pet2)));
        ownerService.save(owner2);

        pet2.setOwner(owner2);
        petService.save(pet2);

        Visit visit1 = new Visit();
        visit1.setDate(LocalDate.of(2024, 12, 30));
        visit1.setDescription("description for visit 1");
        visit1.setPet(pet1);
        visitService.save(visit1);

        pet1.setVisits(new HashSet<>(List.of(visit1)));
        petService.save(pet1);

        Visit visit2 = new Visit();
        visit2.setDate(LocalDate.of(2025, 1, 5));
        visit2.setDescription("description for visit 2");
        visit2.setPet(pet2);
        visitService.save(visit2);

        pet2.setVisits(new HashSet<>(List.of(visit2)));
        petService.save(pet2);

        Specialty specialty1 = new Specialty();
        specialty1.setDescription("radiology");
        specialtyService.save(specialty1);

        Specialty specialty2 = new Specialty();
        specialty2.setDescription("surgery");
        specialtyService.save(specialty2);

        Specialty specialty3 = new Specialty();
        specialty3.setDescription("dentistry");
        specialtyService.save(specialty3);

        Vet vet1 = new Vet();
        vet1.setFirstName("Rose");
        vet1.setLastName("Dou");
        vet1.setSpecialties(new HashSet<>(List.of(specialty1)));
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Ira");
        vet2.setLastName("Qwerty");
        vet2.setSpecialties(new HashSet<>(List.of(specialty2)));
        vetService.save(vet2);
    }
}
