package com.example.petclinic.bootstrap;

import com.example.petclinic.model.Owner;
import com.example.petclinic.model.Pet;
import com.example.petclinic.model.PetType;
import com.example.petclinic.model.Vet;
import com.example.petclinic.services.OwnerService;
import com.example.petclinic.services.PetService;
import com.example.petclinic.services.PetTypeService;
import com.example.petclinic.services.VetService;
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

    public DataLoader(OwnerService ownerService, VetService vetService, PetService petService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType petType1 = new PetType();
        petType1.setName("pet type 1");
        petTypeService.save(petType1);

        PetType petType2 = new PetType();
        petType2.setName("pet type 2");

        Pet pet1 = new Pet();
        pet1.setName("pet 1");
        pet1.setBirthDate(LocalDate.of(2020, 12, 12));
        pet1.setType(petType1);
        pet1 = petService.save(pet1);

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

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setAddress("address for owner 2");
        owner2.setCity("city for owner 2");
        owner2.setTelephone("23456789");
        owner2.setFirstName("Elena");
        owner2.setLastName("Quartz");
        owner2.setPets(new HashSet<>(List.of(pet2)));
        ownerService.save(owner2);

        Vet vet1 = new Vet();
        vet1.setFirstName("Rose");
        vet1.setLastName("Dou");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Ira");
        vet2.setLastName("Qwerty");
        vetService.save(vet2);
    }
}
