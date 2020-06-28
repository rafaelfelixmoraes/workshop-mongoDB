package br.com.rafaelfelix.workshop.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.rafaelfelix.workshop.mongo.domains.PostDomain;

@Repository
public interface PostRepository extends MongoRepository<PostDomain, String>{

}
