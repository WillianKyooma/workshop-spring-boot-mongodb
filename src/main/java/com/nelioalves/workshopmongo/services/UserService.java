package com.nelioalves.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.dto.UserDTO;
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
 
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
		
	}
	
	public User update(User obj) {
	    Optional<User> newObjOptional = repo.findById(obj.getId());
	    if (newObjOptional.isPresent()) {
	        User newObj = newObjOptional.get();
	        updateData(newObj, obj);
	        return repo.save(newObj);
	    } else {
	        // Handle the case where the object with the given ID is not found
	        return null; // or throw an exception
	    }
	}

	private void updateData(User newObj, User obj) {
	    newObj.setName(obj.getName());
	    newObj.setEmail(obj.getEmail());
	}


	public User FromDTO(UserDTO objDto) {
		return new User(objDto.getId(),objDto.getName(),objDto.getEmail());
	}
}
