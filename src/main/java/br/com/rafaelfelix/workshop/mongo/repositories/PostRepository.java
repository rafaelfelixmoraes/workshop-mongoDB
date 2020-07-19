package br.com.rafaelfelix.workshop.mongo.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.rafaelfelix.workshop.mongo.domains.PostDomain;

@Repository
public interface PostRepository extends MongoRepository<PostDomain, String>{

	List<PostDomain> findByTitleContainingIgnoreCase(String text);
	
	@Query("{'title' : {$regex : ?0, $options: 'i'}}")
	List<PostDomain> findByTitle(String text);
	
	@Query("{ $and: [ { 'dateTime': { $gte: ?1 } }, { 'dateTime': { $lte: ?2 } }, "
			+ "{ $or: [ {'title' : {$regex : ?0, $options: 'i'} }, {'body' : {$regex : ?0, $options: 'i'}}, {'comments.text' : {$regex : ?0, $options: 'i'}} ] }  "
			+ "] }")
	List<PostDomain> fullSearch(String text, LocalDateTime minDate, LocalDateTime maxDate);
}
