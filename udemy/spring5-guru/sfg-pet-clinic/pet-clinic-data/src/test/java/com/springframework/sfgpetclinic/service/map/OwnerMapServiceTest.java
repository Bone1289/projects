package com.springframework.sfgpetclinic.service.map;

import com.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {
    final Long ownerId = 1L;
    final String lastName = "Bogdan";
    OwnerMapService ownerMapService;

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        ownerMapService.save(
                Owner.builder()
                        .id(ownerId)
                        .lastName(lastName)
                        .build()
        );
    }

    @Test
    void saveExistingId() {
        Long ownerId = 2L;
        var savedOwner = ownerMapService.save(Owner.builder()
                                                      .id(ownerId)
                                                      .build());

        assertEquals(ownerId, savedOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner saveOwner = ownerMapService.save(Owner.builder()
                                                       .build());

        assertNotNull(saveOwner);
        assertNotNull(saveOwner.getId());
    }

    @Test
    void findAll() {
        var allOwner = ownerMapService.findAll();
        assertEquals(1, allOwner.size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);

        assertEquals(0, ownerMapService.findAll()
                .size());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));

        assertEquals(0, ownerMapService.findAll()
                .size());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void findByLastName() {
        var foundOwner = ownerMapService.findByLastName(lastName);

        assertNotNull(foundOwner);
        assertEquals(lastName, foundOwner.getLastName());

    }

    @Test
    void findByLastNameNotFound() {
        var foundOwner = ownerMapService.findByLastName("Not Found");
        assertNull(foundOwner);

    }
}