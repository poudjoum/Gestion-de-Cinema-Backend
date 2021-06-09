package com.jumpy.Cinema.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.jumpy.Cinema.dao.ProjectionRepository;
import com.jumpy.Cinema.dao.TicketRepository;

public interface ICinemaInitService {
	@Autowired
	ProjectionRepository projectionRepository;
	@Autowired
	TicketRepository ticketRepository;
	public void initVilles();
	public void initCinema();
	public void initSalles();
	public void initPlaces();
	public void initSeances();
	public void initCategories();
	public void initFlims();
	void initTickets();
	void initProjections();
	void initFlims();
	void initCategories();
	void initSeances();
	void initPlaces();
	void initSalles();
	void initCinema();
	void initVilles();
	public void initProjections();
	public void initTickets();
	

}
