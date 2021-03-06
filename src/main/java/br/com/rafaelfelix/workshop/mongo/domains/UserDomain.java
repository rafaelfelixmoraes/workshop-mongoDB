package br.com.rafaelfelix.workshop.mongo.domains;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Document(collection = "users")
public class UserDomain implements Serializable{
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	private String id;
	
	private String name;
	
	private String email;
	
	@DBRef(lazy = true)
	private List<PostDomain> posts = new ArrayList<>();

	public UserDomain(String id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}
}
