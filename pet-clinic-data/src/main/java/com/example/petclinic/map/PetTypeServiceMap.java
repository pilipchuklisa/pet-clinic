package com.example.petclinic.map;

import com.example.petclinic.model.PetType;
import com.example.petclinic.services.PetTypeService;
import org.springframework.stereotype.Service;

@Service
public class PetTypeServiceMap extends AbstractMapService<PetType, Long> implements PetTypeService {
}
