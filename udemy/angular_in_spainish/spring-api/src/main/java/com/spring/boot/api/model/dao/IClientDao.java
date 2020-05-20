package com.spring.boot.api.model.dao;

import com.spring.boot.api.model.entity.Client;
import com.spring.boot.api.model.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IClientDao extends JpaRepository<Client, Long> {

    @Query("from Region")
    List<Region> findAllRegion();
}
