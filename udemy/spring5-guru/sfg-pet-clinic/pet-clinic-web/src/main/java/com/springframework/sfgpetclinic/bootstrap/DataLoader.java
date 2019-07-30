package com.springframework.sfgpetclinic.bootstrap;

import com.springframework.sfgpetclinic.model.*;
import com.springframework.sfgpetclinic.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (petTypeService.findAll() == null || petTypeService.findAll().size() == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType saveDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType saveCatType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality saveRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality saveSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality saveDentistry = specialityService.save(dentistry);

        {
            Owner owner = new Owner();
            owner.setFirstName("Rata");
            owner.setLastName("Bogdan");
            owner.setAddress("123 Stop");
            owner.setCity("Galati");
            owner.setTelephone("12345");

            Pet pet = new Pet();
            pet.setPetType(saveDogType);
            pet.setOwner(owner);
            pet.setBirthDate(LocalDate.now());
            pet.setName("Bobitza");

            owner.getPets().add(pet);
            ownerService.save(owner);

            Visit visit = new Visit();
            visit.setPet(pet);
            visit.setDate(LocalDate.now());
            visit.setDescription("Neck Pain");
        }

        {
            Owner owner = new Owner();
            owner.setFirstName("Mihaela");
            owner.setLastName("Badan");
            owner.setAddress("123 Visana");
            owner.setCity("Bucharest");
            owner.setTelephone("12345");

            Pet pet = new Pet();
            pet.setPetType(saveCatType);
            pet.setOwner(owner);
            pet.setBirthDate(LocalDate.now());
            pet.setName("Gustav");

            owner.getPets().add(pet);
            ownerService.save(owner);
        }
        System.out.println("Loaded Owners...");
        {
            Vet vet = new Vet();
            vet.setFirstName("Badan");
            vet.setLastName("Mihaela");
            vet.getSpecialities().add(saveRadiology);
            vetService.save(vet);
        }
        {
            Vet vet = new Vet();
            vet.setFirstName("Vasile");
            vet.setLastName("Maria");
            vet.getSpecialities().add(saveSurgery);
            vet.getSpecialities().add(saveDentistry);
            vetService.save(vet);
        }
        System.out.println("Loaded Vets...");
    }
}
