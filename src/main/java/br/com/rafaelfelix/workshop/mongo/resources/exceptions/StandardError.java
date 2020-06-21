package br.com.rafaelfelix.workshop.mongo.resources.exceptions;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class StandardError implements Serializable{
	private static final long serialVersionUID = 5061968303699707315L;
	
	private Date timeStamp;
	private Integer status;
	private String error;
	private String message;
	private String path;

	/**
	 * @return the masked TimeStamp
	 */
	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
	public Date getTimeStamp() {
		return timeStamp;
	}
	
}
