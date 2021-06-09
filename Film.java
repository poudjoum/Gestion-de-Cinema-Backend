package com.jumpy.Cinema.entites;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Film {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(length=50)
	private String titre;
	@Column(length=150)
	private String description;
	@Column(length=50)
	private String realisateur;
	@Column(length=50)
	private Date dateSortie;
	private double duree;
	@Column(length=50)
	private String photo;
	@OneToMany(mappedBy="film")
	@JsonProperty(access=Access.WRITE_ONLY)
	private Collection<Projection>projections;
	@ManyToOne
	private Categorie categorie;
}
