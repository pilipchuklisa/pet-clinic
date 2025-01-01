package com.example.petclinic.bootstrap;

import com.example.petclinic.model.Owner;
import com.example.petclinic.model.Vet;
import com.example.petclinic.services.OwnerService;
import com.example.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setFirstName("Eva");
        owner1.setLastName("Stone");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Elena");
        owner2.setLastName("Quartz");
        ownerService.save(owner2);

        Owner owner3 = new Owner();
        owner3.setFirstName("Emma");
        owner3.setLastName("Leaf");
        ownerService.save(owner3);

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
