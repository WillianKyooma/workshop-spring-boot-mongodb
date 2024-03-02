package com.nelioalves.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.repository.UserRepository;
import com.nelioalves.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
	return repo.findAll();	
	}
	
	
	/**
	 * Retorna o usuário com o ID fornecido.
	 * Utiliza findById que retorna um Optional<User> devido à remoção do método findOne do Spring Data MongoDB.
	 * Lança ObjectNotFoundException se o usuário não for encontrado.
	 */
	public User findById(String id) {
	    return repo.findById(id)
	               .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

}