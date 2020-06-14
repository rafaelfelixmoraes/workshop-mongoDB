package br.com.rafaelfelix.workshop.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.rafaelfelix.workshop.mongo.domains.UserDomain;

@Repository
public interface UserRepository extends MongoRepository<UserDomain, String>{

}
