package br.com.rafaelfelix.workshop.mongo.domains;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.rafaelfelix.workshop.mongo.dto.AuthorDTO;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "posts")
public class PostDomain implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	@Id
	private String id;
	
	private LocalDateTime dateTime;
	private String title;
	private String body;
	private AuthorDTO author;

}
