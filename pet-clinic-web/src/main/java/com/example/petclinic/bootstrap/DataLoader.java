package com.example.petclinic.bootstrap;

import com.example.petclinic.model.Owner;
import com.example.petclinic.model.PetType;
import com.example.petclinic.model.Vet;
import com.example.petclinic.services.OwnerService;
import com.example.petclinic.services.PetTypeService;
import com.example.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType petType1 = new PetType();
        petType1.setName("pet type 1");
        petTypeService.save(petType1);

        PetType petType2 = new PetType();
        petType2.setName("pet type 2");
        petTypeService.save(petType2);

        Owner owner1 = new Owner();
        owner1.setFirstName("Eva");
        owner1.setLastName("Stone");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Elena");
        owner2.setLastName("Quartz");
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
