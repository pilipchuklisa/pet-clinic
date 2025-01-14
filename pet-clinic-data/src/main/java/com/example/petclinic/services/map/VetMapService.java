package com.example.petclinic.services.map;

import com.example.petclinic.model.Vet;
import com.example.petclinic.services.SpecialtyService;
import com.example.petclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "map"})
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialtyService specialtyService;

    public VetMapService(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public Vet save(Vet vet) {

        if (vet != null) {
            if (vet.getSpecialties() != null && !vet.getSpecialties().isEmpty()) {
                vet.getSpecialties().forEach(specialty -> {
                    if (specialty.getId() == null) {
                        specialtyService.save(specialty);
                    }
                });
            }
        } else {
            throw new RuntimeException("Cannot save null object");
        }

        return super.save(vet);
    }
}
