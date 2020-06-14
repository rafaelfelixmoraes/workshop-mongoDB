package br.com.rafaelfelix.workshop.mongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rafaelfelix.workshop.mongo.domains.UserDomain;
import br.com.rafaelfelix.workshop.mongo.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	public List<UserDomain> findAll(){
		return userRepo.findAll();
	}
}
