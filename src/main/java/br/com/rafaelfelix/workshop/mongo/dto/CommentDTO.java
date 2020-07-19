package br.com.rafaelfelix.workshop.mongo.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String text;
	private LocalDateTime dateTime;
	private AuthorDTO author;

}
