package com.nelioalves.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.dto.UserDTO;
import com.nelioalves.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

@Autowired
private UserService service;	
	
@GetMapping
public ResponseEntity <List<UserDTO>>FindAll(){

	List<User> list = service.findAll();
	List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
	return ResponseEntity.ok().body(listDto);
	}


@GetMapping(value ="/{id}")
public ResponseEntity <UserDTO>FindById(@PathVariable String id){
    
	User obj = service.findById(id);
	
	
	
	return ResponseEntity.ok().body(new UserDTO(obj));
	}


}
