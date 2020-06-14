package br.com.rafaelfelix.workshop.mongo.domain;

import java.io.Serializable;

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
public class UserDomain implements Serializable{
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	private String id;
	
	private String name;
	
	private String email;
}
