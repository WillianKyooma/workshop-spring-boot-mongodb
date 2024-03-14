package com.nelioalves.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.repository.PostRepository;
import com.nelioalves.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repo;
	
	public List<Post> findAll(){
	return repo.findAll();	
	}
	
	
	public Post findById(String id) {
	    return repo.findById(id)
	               .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
 
}