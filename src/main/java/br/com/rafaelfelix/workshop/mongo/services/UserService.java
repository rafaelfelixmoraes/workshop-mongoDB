package br.com.rafaelfelix.workshop.mongo.services;

import java.util.Arrays;
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
	
	public void saveAll() {
		userRepo.deleteAll();//TODO exclude this line after tests
		
		UserDomain maria = new UserDomain(null, "Maria Brown", "maria@gmail.com");
		UserDomain alex = new UserDomain(null, "Alex Green", "alex@gmail.com");
		UserDomain bob = new UserDomain(null, "Bob Grey", "bob@gmail.com");
		
		userRepo.saveAll(Arrays.asList(maria, alex, bob));
	}
}
