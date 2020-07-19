package br.com.rafaelfelix.workshop.mongo.domains;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.rafaelfelix.workshop.mongo.dto.AuthorDTO;
import br.com.rafaelfelix.workshop.mongo.dto.CommentDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
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
	
	private List<CommentDTO> comments = new ArrayList<>();

	public PostDomain(String id, LocalDateTime dateTime, String title, String body, AuthorDTO author) {
		super();
		this.id = id;
		this.dateTime = dateTime;
		this.title = title;
		this.body = body;
		this.author = author;
	}

}
