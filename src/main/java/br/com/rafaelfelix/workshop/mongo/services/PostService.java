package br.com.rafaelfelix.workshop.mongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rafaelfelix.workshop.mongo.domains.PostDomain;
import br.com.rafaelfelix.workshop.mongo.repositories.PostRepository;
import br.com.rafaelfelix.workshop.mongo.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepo;
	
	public List<PostDomain> findAll(){
		return postRepo.findAll();
	}
	
	public void saveAll() {}
	
	public PostDomain findById(String id) {
		Optional<PostDomain> obj = postRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Post não encontrado"));
	}
	
}
