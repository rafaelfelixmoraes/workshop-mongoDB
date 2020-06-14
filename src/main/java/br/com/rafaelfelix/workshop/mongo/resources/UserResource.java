package br.com.rafaelfelix.workshop.mongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rafaelfelix.workshop.mongo.domains.UserDomain;
import br.com.rafaelfelix.workshop.mongo.dto.UserDTO;
import br.com.rafaelfelix.workshop.mongo.services.UserService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/users")
@ApiModel(description = "Endpoints to manipulate data about Users")
public class UserResource {
	
	@Autowired
	private UserService userService;

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "List all users", response = List.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved users list"),
            @ApiResponse(code = 500, message = "A strange error occured")
	})
	public ResponseEntity<List<UserDTO>> findAll() {
		userService.saveAll(); //Creating database mocks
		List<UserDomain> listObj = userService.findAll();
		List<UserDTO> usersList = listObj.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
		
		return ResponseEntity.ok(usersList);
	}
}
