package com.example.petclinic.map;

import com.example.petclinic.model.Specialty;
import com.example.petclinic.services.SpecialtyService;
import org.springframework.stereotype.Service;

@Service
public class SpecialtyServiceMap extends AbstractMapService<Specialty, Long> implements SpecialtyService {
}
