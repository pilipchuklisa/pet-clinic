package com.example.petclinic.services.map;

import com.example.petclinic.model.Owner;
import com.example.petclinic.model.Pet;
import com.example.petclinic.model.PetType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;

    final Long idValue = 1L;
    final String lastNameValue = "lastname";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());

        PetType petType = new PetType();
        petType.setName("pet type");

        Pet pet = new Pet();
        pet.setName("pet");
        pet.setBirthDate(LocalDate.of(2020, 12, 12));
        pet.setType(petType);

        Owner owner = new Owner();
        owner.setId(idValue);
        owner.setLastName(lastNameValue);
        owner.setPets(new HashSet<>(List.of(pet)));
        ownerMapService.save(owner);
    }

    @Test
    void saveNullOwner() {
        Assertions.assertThrows(RuntimeException.class,() -> ownerMapService.save(null));
    }

    @Test
    void saveWithId() {
        Long idValue = 2L;
        Owner owner = new Owner();
        owner.setId(idValue);

        Assertions.assertEquals(idValue, ownerMapService.save(owner).getId());
    }

    @Test
    void saveWithOutId() {
        Owner owner = ownerMapService.save(new Owner());

        Assertions.assertNotNull(owner);
        Assertions.assertNotNull(owner.getId());
    }

    @Test
    void saveNullPetType() {
        Pet pet = new Pet();
        Owner owner = new Owner();
        owner.setPets(new HashSet<>(List.of(pet)));

        Assertions.assertThrows(RuntimeException.class,() -> ownerMapService.save(owner));
    }

    @Test
    void findByLastName() {
        Assertions.assertEquals(lastNameValue, ownerMapService.findByLastName(lastNameValue).getLastName());
    }

    @Test
    void findByLastNameNotFound() {
        Assertions.assertNull(ownerMapService.findByLastName(""));
    }

    @Test
    void findById() {
        Assertions.assertEquals(idValue, ownerMapService.findById(idValue).getId());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerMapService.findAll();

        Assertions.assertEquals(1, owners.size());
    }

    @Test
    void delete() {
        Owner owner = ownerMapService.findById(idValue);
        ownerMapService.delete(owner);

        Assertions.assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(idValue);

        Assertions.assertNull(ownerMapService.findById(idValue));
    }
}