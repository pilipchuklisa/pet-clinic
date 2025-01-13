package com.example.petclinic.services;

import com.example.petclinic.model.Owner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

    Page<Owner> findByLastNameStartingWith(String lastname, Pageable pageable);
}
