package com.example.petclinic.services.map;

import com.example.petclinic.model.Specialty;
import com.example.petclinic.services.SpecialtyService;
import org.springframework.stereotype.Service;

@Service
public class SpecialtyServiceMap extends AbstractMapService<Specialty, Long> implements SpecialtyService {
}
