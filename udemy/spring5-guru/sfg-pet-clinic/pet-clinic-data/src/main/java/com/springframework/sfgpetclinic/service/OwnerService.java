package com.springframework.sfgpetclinic.service;

import com.springframework.sfgpetclinic.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String name);

    List<Owner> findAllByLastNameLike(String name);
}
