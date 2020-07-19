package br.com.rafaelfelix.workshop.mongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class URL {
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	
	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text, StandardCharsets.UTF_8.name());
		} catch (UnsupportedEncodingException e) {
			return new String("");
		}
	}
	
	public static LocalDateTime convertDateTime(String textDate, LocalDateTime defaultDate) {
		try {
			return LocalDateTime.parse(decodeParam(textDate), formatter);
		} catch (DateTimeParseException e) {
			return defaultDate;
		}
	}

}
