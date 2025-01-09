package com.example.petclinic.services.springdatajpa;

import com.example.petclinic.model.Owner;
import com.example.petclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    @InjectMocks
    OwnerSDJpaService service;

    @Mock
    OwnerRepository repository;

    final Long ID_VALUE = 1L;
    final String LAST_NAME_VALUE = "lastname";

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByLastName() {
        Owner owner = Owner.builder().lastName(LAST_NAME_VALUE).build();

        Mockito.when(repository.findByLastName(Mockito.any())).thenReturn(owner);

        Assertions.assertEquals(LAST_NAME_VALUE, service.findByLastName(LAST_NAME_VALUE).getLastName());
    }

    @Test
    void findByLastNameNotFound() {
        Mockito.when(repository.findByLastName(Mockito.any())).thenReturn(null);
        Assertions.assertNull(service.findByLastName(null));
    }

    @Test
    void findById() {
        Owner owner = Owner.builder().id(ID_VALUE).build();

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.ofNullable(owner));

        Assertions.assertEquals(ID_VALUE, service.findById(ID_VALUE).getId());
    }

    @Test
    void findByIdNotFound() {
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertNull(service.findById(2L));
    }

    @Test
    void findAll() {
        Owner owner = new Owner();
        Set<Owner> owners = new HashSet<>();
        owners.add(owner);

        Mockito.when(repository.findAll()).thenReturn(owners);

        Assertions.assertEquals(owners.size(), service.findAll().size());
    }

    @Test
    void save() {
        Owner owner = new Owner();

        Mockito.when(repository.save(Mockito.any())).thenReturn(owner);

        Owner savedOwner = service.save(owner);

        Assertions.assertNotNull(savedOwner);
    }

    @Test
    void delete() {
        Owner owner = new Owner();
        service.delete(owner);

        Mockito.verify(repository, Mockito.times(1)).delete(Mockito.any());
    }

    @Test
    void deleteById() {
        service.deleteById(ID_VALUE);

        Mockito.verify(repository).deleteById(Mockito.anyLong());
    }
}