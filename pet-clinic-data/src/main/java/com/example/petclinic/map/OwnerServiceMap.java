package com.example.petclinic.map;

import com.example.petclinic.model.Owner;
import com.example.petclinic.services.OwnerService;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    @Override
    public Owner findByLastName(String lastName) {
        return super.findAll().stream()
                .filter(o -> o.getLastName().equals(lastName))
                .findAny()
                .orElse(null);
    }
}
