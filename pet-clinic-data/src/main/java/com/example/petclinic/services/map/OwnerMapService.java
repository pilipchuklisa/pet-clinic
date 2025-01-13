package com.example.petclinic.services.map;

import com.example.petclinic.model.Owner;
import com.example.petclinic.services.OwnerService;
import com.example.petclinic.services.PetService;
import com.example.petclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "map"})
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerMapService(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Owner save(Owner owner) {
        if (owner != null) {
            if (owner.getPets() != null) {
                owner.getPets().forEach(pet -> {
                    if (pet.getType() != null) {
                        if (pet.getType().getId() == null) {
                            petTypeService.save(pet.getType());
                        }
                    } else {
                        throw new RuntimeException("PetType is required");
                    }

                    pet.setOwner(owner);
                    if (pet.getId() == null) {
                        petService.save(pet);
                    }
                });
            }
        } else {
            throw new RuntimeException("Object cannot be null");
        }
        return super.save(owner);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return super.findAll().stream()
                .filter(o -> o.getLastName().equals(lastName))
                .findAny()
                .orElse(null);
    }

    @Override
    public Page<Owner> findByLastNameStartingWith(String lastname, Pageable pageable) {
        throw new RuntimeException("findByLastNameStartingWith(String lastname, Pageable pageable) method not implemented inside OwnerMapService");
    }
}
