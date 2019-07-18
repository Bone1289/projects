package com.springframework.sfgpetclinic.bootstrap;

import com.springframework.sfgpetclinic.model.Owner;
import com.springframework.sfgpetclinic.model.Vet;
import com.springframework.sfgpetclinic.service.OwnerService;
import com.springframework.sfgpetclinic.service.VetService;
import com.springframework.sfgpetclinic.service.map.OwnerServiceMap;
import com.springframework.sfgpetclinic.service.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader() {
        this.ownerService = new OwnerServiceMap();
        this.vetService = new VetServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {
        {
            Owner owner = new Owner();
            owner.setId(1L);
            owner.setFirstName("Rata");
            owner.setFirstName("Bogdan");
            ownerService.save(owner);
        }

        {
            Owner owner = new Owner();
            owner.setId(2L);
            owner.setFirstName("Rata");
            owner.setFirstName("Andrei");
            ownerService.save(owner);
        }
        System.out.println("Loaded Owners...");
        {
            Vet vet = new Vet();
            vet.setId(1L);
            vet.setFirstName("Badan");
            vet.setLastName("Mihaela");
            vetService.save(vet);
        }
        {
            Vet vet = new Vet();
            vet.setId(2L);
            vet.setFirstName("Vasile");
            vet.setLastName("Maria");
            vetService.save(vet);
        }
        System.out.println("Loaded Vets...");
    }
}
