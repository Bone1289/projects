package com.springframework.sfgpetclinic.service.jpa;

import com.springframework.sfgpetclinic.model.Owner;
import com.springframework.sfgpetclinic.repositories.OwnerRepository;
import com.springframework.sfgpetclinic.repositories.PetRepository;
import com.springframework.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerJpaServiceTest {

    public static final String LAST_NAME = "Bogdan";
    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerJpaService ownerJpaService;

    Owner returnedOwner;

    @BeforeEach
    void setUp() {
        returnedOwner = Owner.builder()
                .id(1L)
                .lastName(LAST_NAME)
                .build();
    }

    @Test
    void findByLastName() {
        when(ownerJpaService.findByLastName(any())).thenReturn(returnedOwner);

        Owner smith = ownerJpaService.findByLastName(LAST_NAME);

        assertEquals(LAST_NAME, smith.getLastName());

        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findAll() {
        Set<Owner> returnedOwnersSet = new HashSet<>();

        returnedOwnersSet.add(Owner.builder()
                                      .id(1L)
                                      .build());
        returnedOwnersSet.add(Owner.builder()
                                      .id(2L)
                                      .build());

        when(ownerRepository.findAll()).thenReturn(returnedOwnersSet);

        Set<Owner> owners = ownerJpaService.findAll();

        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnedOwner));

        Owner owner = ownerJpaService.findById(1L);

        assertNotNull(owner);
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner owner = ownerJpaService.findById(2L);

        assertNull(owner);
    }

    @Test
    void save() {
        when(ownerRepository.save(any())).thenReturn(returnedOwner);

        Owner ownerToSave = Owner.builder()
                .id(1L)
                .build();
        Owner saveOwner = ownerJpaService.save(ownerToSave);

        assertNotNull(saveOwner);

        verify(ownerRepository).save(any());

    }

    @Test
    void delete() {
        ownerJpaService.delete(returnedOwner);
        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        ownerJpaService.deleteById(1L);
        verify(ownerRepository, times(1)).deleteById(anyLong());
    }
}