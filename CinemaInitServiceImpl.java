package com.jumpy.Cinema.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumpy.Cinema.dao.CategorieRepository;
import com.jumpy.Cinema.dao.CinemaRepository;
import com.jumpy.Cinema.dao.FilmRepository;
import com.jumpy.Cinema.dao.PlaceRepository;
import com.jumpy.Cinema.dao.SalleRepository;
import com.jumpy.Cinema.dao.SeanceRepository;
import com.jumpy.Cinema.dao.VilleRepository;
import com.jumpy.Cinema.entites.Categorie;
import com.jumpy.Cinema.entites.Cinema;
import com.jumpy.Cinema.entites.Film;
import com.jumpy.Cinema.entites.Place;
import com.jumpy.Cinema.entites.Salle;
import com.jumpy.Cinema.entites.Seance;
import com.jumpy.Cinema.entites.Ticket;
import com.jumpy.Cinema.entites.Ville;
import com.jumpy.Cinema.entites.Projection;

@Service
@Transactional
public class CinemaInitServiceImpl implements ICinemaInitService {
@Autowired
	
	private VilleRepository villeRepository;
@Autowired
	private CinemaRepository cinemaRepository;
@Autowired
private SalleRepository salleRepository;
@Autowired
private CategorieRepository categoryRepository;
@Autowired
private PlaceRepository placerepository;
@Autowired
private SeanceRepository seanceRepository;
@Autowired
private FilmRepository filmRepository;
@Override
	public void initVilles() {
		Stream.of("Douala","Yaoundé","Bafoussam","Kribi").forEach(nameVille->{
			Ville ville=new Ville();
			ville.setName(nameVille);
			villeRepository.save(ville);
		});
		
	}
	@Override
	public void initCinema() {
		villeRepository.findAll().forEach(v->{
			Stream.of("Eden","paradis","Empire","Kondi","Wouri","Abbia").
			forEach(nameCinema->{
				Cinema cinema=new Cinema();
				cinema.setName(nameCinema);
				cinema.setNombreSalles(2+(int)(Math.random()*3));
				cinema.setVille(v);
				cinemaRepository.save(cinema);
			});
			
		});
		
	}

	@Override
	public void initSalles() {
		cinemaRepository.findAll().forEach(cinema->{
			for(int i=0;i<cinema.getNombreSalles();i++) {
				Salle s=new Salle();
				s.setName("Salle"+(i+1));
				s.setCinema(cinema);
				s.setNombrePlace(50+(int)(Math.random()*20));
				salleRepository.save(s);
			}
		});
		
	}

	@Override
	public void initPlaces() {
		salleRepository.findAll().forEach(s->{
			for(int i=0;i<s.getNombrePlace();i++) {
				Place p=new Place();
				p.setNumero(i+1);
				p.setSalle(s);
				placerepository.save(p);
			}
		});
		
	}

	@Override
	public void initSeances() {
		DateFormat df=new SimpleDateFormat("HH:mm");
		Stream.of("12:00","14:00","17:00","19:00","21:00").forEach(s->{
			Seance seance=new Seance();
			try {
				seance.setHeureDebut(df.parse(s));
				seanceRepository.save(seance);
			}catch(ParseException e) {
				e.printStackTrace();
			}
		});
		
	}

	@Override
	public void initCategories() {
		Stream.of("Histoire","Action","Romance","Drama","Thriller").forEach(c->{
			Categorie cat=new Categorie();
			cat.setName(c);
			categoryRepository.save(cat);
		});
		
	}

	@Override
	public void initFlims() {
		List<Categorie> cat=categoryRepository.findAll();
		double[] durees =new double[] {1.5,2,2.5,3};
		Stream.of("forrest Gump","Ligne verte","Spiderman","superman","le parrain").forEach(titreFilm->{
			Film film=new Film();
			film.setTitre(titreFilm);
			film.setDuree(durees[new Random().nextInt(durees.length)]);
			film.setPhoto(titreFilm.replaceAll(" ","")+".jpg");
			film.setCategorie(cat.get(new Random().nextInt(cat.size())));
			filmRepository.save(film);
		});
	}

	@Override
	public void initProjections() {
		double[] prices =new double[] {1000,1500,2000,2500,3000};
		villeRepository.findAll().forEach(ville->{
			ville.getCinema().forEach(cinema->{
				cinema.getSalles().forEach(salle->{
					filmRepository.findAll().forEach(film->{
						seanceRepository.findAll().forEach(seance->{
							Projection projection=new Projection();
							projection.setDateProjection(new Date());
							projection.setFilm(film);
							projection.setSalle(salle);
							projection.setSeance(seance);
							projection.setPrix(prices[new Random().nextInt(prices.length)]);
							projectionRepository.save(projection);
						});
					});
				});
			});
		});
		
	}

	@Override
	public void initTickets() {
		projectionRepository.findAll().forEach(p->{
			p.getSalle().getPlaces().forEach(place->{
				Ticket tick=new Ticket();
				tick.setPlace(place);
				tick.setPrix(p.getPrix());
				tick.setProjection(p);
				tick.setReservee(false);
				ticketRepository.save(tick);
			});
		});
	}

}
