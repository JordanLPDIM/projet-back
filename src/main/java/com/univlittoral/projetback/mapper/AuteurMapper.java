package com.univlittoral.projetback.mapper;

import java.util.ArrayList;
import java.util.List;

import com.univlittoral.projetback.dto.AuteurDTO;
import com.univlittoral.projetback.dto.LivreDTO;
import com.univlittoral.projetback.entity.AuteurEntity;
import com.univlittoral.projetback.entity.LivreEntity;

public class AuteurMapper {
	
public static AuteurDTO map(AuteurEntity entity) {
		
		AuteurDTO auteur = new AuteurDTO();
		
		auteur.setId(entity.getId());
		auteur.setPrenom(entity.getPrenom());
		auteur.setNom(entity.getNom());
		auteur.setDateNaissance(entity.getDateNaissance());
		
		return auteur;
			
	}


public static List<AuteurDTO> map(List<AuteurEntity> auteurEntities){
	
	List<AuteurDTO> result = new ArrayList<AuteurDTO>();
	
	for(final AuteurEntity entity : auteurEntities) {
		result.add(AuteurMapper.map(entity));
	}
	
	return result;
		
	
}

}
