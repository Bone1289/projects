package com.springframework.sfgpetclinic.service.jpa;

import com.springframework.sfgpetclinic.model.PetType;
import com.springframework.sfgpetclinic.repositories.PetTypeRepository;
import com.springframework.sfgpetclinic.service.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class PetTypeJpaService implements PetTypeService {

    private final PetTypeRepository petTypeRepository;

    public PetTypeJpaService(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Set<PetType> findAll() {
        Set<PetType> petTypes = new HashSet<>();
        petTypeRepository.findAll()
                         .forEach(petTypes::add);
        return petTypes;
    }

    @Override
    public PetType findById(Long petTypeId) {
        return petTypeRepository.findById(petTypeId)
                                .orElse(null);
    }

    @Override
    public PetType save(PetType petType) {
        return petTypeRepository.save(petType);
    }

    @Override
    public void delete(PetType petType) {
        petTypeRepository.delete(petType);
    }

    @Override
    public void deleteById(Long petTypeId) {
        petTypeRepository.deleteById(petTypeId);
    }
}
