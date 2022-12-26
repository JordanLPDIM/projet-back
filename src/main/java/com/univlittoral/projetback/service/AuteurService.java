package com.univlittoral.projetback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.univlittoral.projetback.entity.AuteurEntity;
import com.univlittoral.projetback.entity.LivreEntity;
import com.univlittoral.projetback.repository.AuteurRepository;
import com.univlittoral.projetback.repository.LivreRepository;

@Service
public class AuteurService {

	@Autowired
	private AuteurRepository repo;
	
	public List<AuteurEntity> findAll(){
		return repo.findAll();
			
	}
	
	public AuteurEntity findById(Long id) {
		return repo.findById(id).orElse(null);
	}

	
	

	
	
}

