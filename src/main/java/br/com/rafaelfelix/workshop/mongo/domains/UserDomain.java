package br.com.rafaelfelix.workshop.mongo.domains;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class UserDomain implements Serializable{
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	private String id;
	
	private String name;
	
	private String email;
}
