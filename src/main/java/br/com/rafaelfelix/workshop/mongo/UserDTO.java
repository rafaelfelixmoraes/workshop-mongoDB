package br.com.rafaelfelix.workshop.mongo;

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
public class UserDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	private String id;
	
	private String name;
	
	private String email;
	
	public UserDTO(UserDomain obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
	}
}
