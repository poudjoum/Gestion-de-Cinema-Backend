package com.jumpy.Cinema.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.jumpy.Cinema.entites.Film;
@RepositoryRestResource
public interface FilmRepository extends JpaRepository<Film, Long> {

}
