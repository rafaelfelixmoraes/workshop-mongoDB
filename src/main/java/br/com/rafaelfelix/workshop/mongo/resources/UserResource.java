package br.com.rafaelfelix.workshop.mongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rafaelfelix.workshop.mongo.domain.UserDomain;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@GetMapping("")
	@ApiOperation(value = "List all users", response = List.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved users list")
	})
	public ResponseEntity<List<UserDomain>> findAll() {
		List<UserDomain> usersList = new ArrayList<>();
		UserDomain maria = new UserDomain("1001", "Maria Brown", "maria@gmail.com");
		UserDomain alex = new UserDomain("1002", "Alex Green", "alex@gmail.com");
		usersList.addAll(Arrays.asList(maria, alex));
		
		return ResponseEntity.ok(usersList);
	}
}
