package com.springframework.sfgpetclinic.bootstrap;

import com.springframework.sfgpetclinic.model.Owner;
import com.springframework.sfgpetclinic.model.PetType;
import com.springframework.sfgpetclinic.model.Vet;
import com.springframework.sfgpetclinic.service.OwnerService;
import com.springframework.sfgpetclinic.service.PetTypeService;
import com.springframework.sfgpetclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
            ownerService.save(owner);
        }

        {
            Owner owner = new Owner();
            owner.setFirstName("Rata");
            owner.setLastName("Andrei");
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
