package br.com.rafaelfelix.workshop.mongo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import br.com.rafaelfelix.workshop.mongo.domains.UserDomain;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class NewUserDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	private String id;
	
	@NotEmpty(message = "Nome obritório")
	private String name;
	
	@NotEmpty(message = "Email obrigatório")
	private String email;
	
	public NewUserDTO(UserDomain obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
	}
}
