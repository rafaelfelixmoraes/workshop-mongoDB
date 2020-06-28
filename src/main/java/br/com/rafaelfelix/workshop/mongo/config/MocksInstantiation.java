package br.com.rafaelfelix.workshop.mongo.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.rafaelfelix.workshop.mongo.domains.PostDomain;
import br.com.rafaelfelix.workshop.mongo.domains.UserDomain;
import br.com.rafaelfelix.workshop.mongo.dto.AuthorDTO;
import br.com.rafaelfelix.workshop.mongo.repositories.PostRepository;
import br.com.rafaelfelix.workshop.mongo.repositories.UserRepository;

@Configuration
public class MocksInstantiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PostRepository postRepo;

	@Override
	public void run(String... args) throws Exception {
		userRepo.deleteAll();//TODO exclude this line after tests
		postRepo.deleteAll();//TODO exclude this line after tests
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		
		UserDomain maria = new UserDomain(null, "Maria Brown", "maria@gmail.com");
		UserDomain alex = new UserDomain(null, "Alex Green", "alex@gmail.com");
		UserDomain bob = new UserDomain(null, "Bob Grey", "bob@gmail.com");
		
		userRepo.saveAll(Arrays.asList(maria, alex, bob));
		
		
		PostDomain post1 = new PostDomain(
				null, 
				LocalDateTime.parse("21/03/2018 12:22:01", formatter), 
				"Partiu viagem", 
				"Vou viajar para São Paulo. Abraços!",
				new AuthorDTO(maria));
		
		PostDomain post2 = new PostDomain(
				null, 
				LocalDateTime.parse("23/03/2018 07:00:44", formatter), 
				"Bom dia!", 
				"Acordei feliz hoje, uhull!!",
				new AuthorDTO(maria));
		
		postRepo.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepo.save(maria);
	}
}
