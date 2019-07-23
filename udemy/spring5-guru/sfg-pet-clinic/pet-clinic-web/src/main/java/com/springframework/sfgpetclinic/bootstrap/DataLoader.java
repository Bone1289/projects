package com.springframework.sfgpetclinic.bootstrap;

import com.springframework.sfgpetclinic.model.Owner;
import com.springframework.sfgpetclinic.model.Pet;
import com.springframework.sfgpetclinic.model.PetType;
import com.springframework.sfgpetclinic.model.Vet;
import com.springframework.sfgpetclinic.service.OwnerService;
import com.springframework.sfgpetclinic.service.PetTypeService;
import com.springframework.sfgpetclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType saveDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType saveCatType = petTypeService.save(cat);

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
            vetService.save(vet);
        }
        {
            Vet vet = new Vet();
            vet.setFirstName("Vasile");
            vet.setLastName("Maria");
            vetService.save(vet);
        }
        System.out.println("Loaded Vets...");
    }
}
