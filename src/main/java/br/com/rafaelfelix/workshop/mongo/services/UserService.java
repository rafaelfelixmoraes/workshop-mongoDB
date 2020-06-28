package br.com.rafaelfelix.workshop.mongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rafaelfelix.workshop.mongo.domains.UserDomain;
import br.com.rafaelfelix.workshop.mongo.dto.NewUserDTO;
import br.com.rafaelfelix.workshop.mongo.repositories.UserRepository;
import br.com.rafaelfelix.workshop.mongo.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	public List<UserDomain> findAll(){
		return userRepo.findAll();
	}
	
	public void saveAll() {}
	
	public UserDomain findById(String id) {
		Optional<UserDomain> obj = userRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
	}
	
	public UserDomain insert(UserDomain obj) {
		return userRepo.insert(obj);
	}
	
	public UserDomain update(UserDomain obj) {
		UserDomain newUser = this.findById(obj.getId());
		updateData(newUser, obj);
		return userRepo.save(newUser);
	}
	
	public void delete(String id) {
		UserDomain userObj = this.findById(id);
		userRepo.delete(userObj);
	}
	
	public UserDomain fromDTO(NewUserDTO dto) {
		return new UserDomain(null, dto.getName(), dto.getEmail());
	}
	
	private void updateData(UserDomain newUser, UserDomain oldUser) {
		newUser.setName(oldUser.getName());
		newUser.setEmail(oldUser.getEmail());
	}
}
