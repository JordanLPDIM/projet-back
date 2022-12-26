package com.univlittoral.projetback.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.univlittoral.projetback.dto.AuteurDTO;
import com.univlittoral.projetback.dto.HomeDTO;
import com.univlittoral.projetback.dto.IndicateurDTO;
import com.univlittoral.projetback.dto.LivreDTO;
import com.univlittoral.projetback.dto.TypeEnum;
import com.univlittoral.projetback.entity.LivreEntity;
import com.univlittoral.projetback.mapper.AuteurMapper;
import com.univlittoral.projetback.mapper.LivreMapper;
import com.univlittoral.projetback.service.AuteurService;
import com.univlittoral.projetback.service.LivreService;


@RestController //-> est utilisé pour marquer les classes en tant que contrôleur Spring.
@RequestMapping(value = "rest/bd") //=> l’URL d’accès à votre controller.
public class HomeController {

	@Autowired
	private LivreService service;
	
	@Autowired
	private AuteurService service2;
	
	@GetMapping(value = "home") 
	public HomeDTO findAll() { 
		  
		  HomeDTO myHome = new HomeDTO();
		  
		  List<TypeEnum> listGenre = new ArrayList<TypeEnum>();
		  
		  for(LivreEntity l : service.findAll()) {
			  if(!listGenre.contains(l.getGenre())) {
				  listGenre.add(l.getGenre());
			  }
		  }
		  
		  IndicateurDTO indic = new IndicateurDTO();
		  indic.setNbGenres(listGenre.size());
		 
		  myHome.setLivres(LivreMapper.map(service.findAll()));
		  
		  indic.setNbLivres(LivreMapper.map(service.findAll()).size());
		 
		  myHome.setAuteurs(AuteurMapper.map(service2.findAll()));
		  
		  indic.setNbAuteurs(service2.findAll().size());
		  
		  myHome.setIndicateurs(indic);
		  
		  return myHome;
	   
	  }
	
	@GetMapping(value="/livres/{id}")
	public LivreDTO findOne(@PathVariable Long id)
	{
		return LivreMapper.map(service.findById(id)); 
    }
	
	@DeleteMapping(value="/livres/{id}")
	public void deleteLivre(@PathVariable Long id) {
		service.deleteById(id);
	}
	
	@PostMapping(value="/livres")
	public void addLivre(@RequestBody LivreDTO livre) {
		
		LivreEntity livreEntity = new LivreEntity();
		service.saveEntity(LivreMapper.mapDTOtoEntity(livre));
	}
	
	@PutMapping(value="/livres/{id}") 
	public void putLivre(@RequestBody LivreDTO livreDTO, @PathVariable Long id) { 
	        LivreEntity livreUpdate = service.findById(id);
	        service.saveEntity(LivreMapper.mapPutEntity(livreUpdate, livreDTO));
	    }
	
	@GetMapping(value="/auteurs/{id}")
	public AuteurDTO findOneAuthor(@PathVariable Long id)
	{
		return AuteurMapper.map(service2.findById(id)); 
    }
	
	@GetMapping(value="/auteurs")
	public List<AuteurDTO> findAuthors()
	{
		return AuteurMapper.map(service2.findAll()); 
    }
	 
}
