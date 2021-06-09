package com.jumpy.Cinema.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.jumpy.Cinema.entites.Salle;
@RepositoryRestResource
public interface SalleRepository extends JpaRepository<Salle, Long>{

}
