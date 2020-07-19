package br.com.rafaelfelix.workshop.mongo.resources;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.rafaelfelix.workshop.mongo.domains.PostDomain;
import br.com.rafaelfelix.workshop.mongo.resources.util.URL;
import br.com.rafaelfelix.workshop.mongo.services.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/posts")
@Api("Endpoints to manipulate Posts")
public class PostResource {
	
	@Autowired
	private PostService postService;
	
	private static final String defaultInitialDate = "2000-01-01T00:00:01";

	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "List all posts", response = List.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved posts list"),
            @ApiResponse(code = 500, message = "A strange error occured")
	})
	public ResponseEntity<?> findAll() {
		postService.saveAll(); //Creating database mocks
		List<PostDomain> listObj = postService.findAll();
		
		return ResponseEntity.ok(listObj);
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get a Post by ID", response = PostDomain.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved post"),
            @ApiResponse(code = 500, message = "A strange error occured"),
            @ApiResponse(code = 404, message = "Post not found")
	})
	public ResponseEntity<PostDomain> findById(@Valid @NotEmpty @PathVariable String id){
		PostDomain postObj = postService.findById(id);
		
		return ResponseEntity.ok(postObj);
	}
	
	@GetMapping(path = "/titlesearch", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get a list os Posts by title containing a text", response = PostDomain.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved post"),
            @ApiResponse(code = 500, message = "A strange error occured"),
            @ApiResponse(code = 404, message = "Post not found")
	})
	public ResponseEntity<List<PostDomain>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text){
		text = URL.decodeParam(text);
		List<PostDomain> postList = postService.findByTitle(text);
		
		return ResponseEntity.ok(postList);
	}
	
	@GetMapping(path = "/fullsearch", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get a list os Posts by Date time interval and by tile, body or comments containing a text", 
				  response = PostDomain.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved post"),
            @ApiResponse(code = 500, message = "A strange error occured"),
            @ApiResponse(code = 400, message = "A datetime parse error"),
            @ApiResponse(code = 404, message = "Post not found")
	})
	public ResponseEntity<List<PostDomain>> fullSearch(
			@RequestParam(value = "text", defaultValue = "") String text,
			@RequestParam(value = "minDate", defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate){
		
		text = URL.decodeParam(text);
		LocalDateTime minDateTime = URL.convertDateTime(minDate, LocalDateTime.parse(defaultInitialDate));
		LocalDateTime maxDateTime = URL.convertDateTime(maxDate, LocalDateTime.now());
		List<PostDomain> postList = postService.fullSearch(text, minDateTime, maxDateTime);
		
		return ResponseEntity.ok(postList);
	}
}
