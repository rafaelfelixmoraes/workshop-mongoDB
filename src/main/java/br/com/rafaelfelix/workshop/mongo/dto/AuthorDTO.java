package br.com.rafaelfelix.workshop.mongo.dto;

import java.io.Serializable;

import br.com.rafaelfelix.workshop.mongo.domains.UserDomain;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AuthorDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	private String id;
	
	private String name;
	
	public AuthorDTO(UserDomain user) {
		this.id = user.getId();
		this.name = user.getName();
	}

}
