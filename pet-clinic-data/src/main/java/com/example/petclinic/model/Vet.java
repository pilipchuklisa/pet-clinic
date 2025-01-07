package com.example.petclinic.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "vets")
public class Vet extends Person {

    @ManyToMany
    @JoinTable(
            name = "vet_specialties",
            joinColumns = @JoinColumn(name = "vet_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id")
    )
    private Set<Specialty> specialties;

    public Set<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(Set<Specialty> specialties) {
        this.specialties = specialties;
    }
}
