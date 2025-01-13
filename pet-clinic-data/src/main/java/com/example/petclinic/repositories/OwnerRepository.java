package com.example.petclinic.repositories;

import com.example.petclinic.model.Owner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Owner findByLastName(String lastName);

    Page<Owner> findByLastNameStartingWith(String lastname, Pageable pageable);
}
