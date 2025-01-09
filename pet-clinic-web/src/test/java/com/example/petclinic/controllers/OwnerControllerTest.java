package com.example.petclinic.controllers;

import com.example.petclinic.model.Owner;
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

import java.util.HashSet;
import java.util.Set;


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
}