package com.example.petclinic.controllers;

import com.example.petclinic.model.Owner;
import com.example.petclinic.model.Pet;
import com.example.petclinic.model.PetType;
import com.example.petclinic.model.Visit;
import com.example.petclinic.services.OwnerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @InjectMocks
    OwnerController controller;

    @Mock
    OwnerService service;

    @Mock
    Model model;

    MockMvc mockMvc;

    Set<Owner> owners = new HashSet<>();

    private static final Long TEST_OWNER_ID = 3L;

    private Owner george() {

        Owner george = new Owner();
        george.setId(TEST_OWNER_ID);
        george.setFirstName("George");
        george.setLastName("Franklin");
        george.setAddress("110 W. Liberty St.");
        george.setCity("Madison");
        george.setTelephone("6085551023");

        PetType dog = new PetType();
        dog.setName("dog");

        Visit visit = new Visit();
        visit.setDate(LocalDate.now());

        Pet max = new Pet();
        max.setType(dog);
        max.setName("Max");
        max.setBirthDate(LocalDate.now());
        max.setVisits(new HashSet<>(List.of(visit)));

        george.setPets(new HashSet<>(List.of(max)));
        max.setId(1L);

        return george;
    }

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());
    }

    @Test
    void index() throws Exception {
        Mockito.when(service.findAll()).thenReturn(owners);

        mockMvc.perform(MockMvcRequestBuilders.get("/owners"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/index"))
                .andExpect(MockMvcResultMatchers.model().attribute("owners", Matchers.hasSize(2)));

        Mockito.verify(service).findAll();
    }

    @Test
    void findAllOwners() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/find"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("notimplemented"));

        Mockito.verifyNoInteractions(service);
    }

    @Test
    void testShowOwner() throws Exception {

        Mockito.when(service.findById(TEST_OWNER_ID)).thenReturn(george());

        mockMvc.perform(get("/owners/{ownerId}", TEST_OWNER_ID))
                .andExpect(status().isOk())
                .andExpect(model().attribute("owner", hasProperty("lastName", is("Franklin"))))
                .andExpect(model().attribute("owner", hasProperty("firstName", is("George"))))
                .andExpect(model().attribute("owner", hasProperty("address", is("110 W. Liberty St."))))
                .andExpect(model().attribute("owner", hasProperty("city", is("Madison"))))
                .andExpect(model().attribute("owner", hasProperty("telephone", is("6085551023"))))
                .andExpect(model().attribute("owner", hasProperty("pets", not(empty()))))
                .andExpect(model().attribute("owner",
                        hasProperty("pets", hasItem(hasProperty("visits", hasSize(greaterThan(0)))))))
                .andExpect(view().name("owners/ownerDetails"));
    }
}