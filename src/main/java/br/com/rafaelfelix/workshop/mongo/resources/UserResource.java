package br.com.rafaelfelix.workshop.mongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.rafaelfelix.workshop.mongo.domains.UserDomain;
import br.com.rafaelfelix.workshop.mongo.dto.NewUserDTO;
import br.com.rafaelfelix.workshop.mongo.dto.UserDTO;
import br.com.rafaelfelix.workshop.mongo.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/users")
@Api("Endpoints to manipulate Users")
public class UserResource {
	
	@Autowired
	private UserService userService;

	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
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
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get a User by ID", response = List.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved user"),
            @ApiResponse(code = 500, message = "A strange error occured"),
            @ApiResponse(code = 404, message = "User not found")
	})
	public ResponseEntity<UserDTO> findById(@Valid @NotEmpty @PathVariable String id){
		UserDomain userObj = userService.findById(id);
		
		return ResponseEntity.ok(new UserDTO(userObj));
	}
	
	@ApiOperation(value = "Create a new User")
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created User"),
            @ApiResponse(code = 500, message = "A strange error occured"),
            @ApiResponse(code = 400, message = "Validation error")
	})
	@PostMapping(path = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE, 
			                       produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> insert(@Valid @RequestBody NewUserDTO requestDTO){
		UserDomain userEntity = userService.insert(userService.fromDTO(requestDTO));
		URI uri =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(userEntity.getId());
		
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted User"),
            @ApiResponse(code = 500, message = "A strange error occured"),
            @ApiResponse(code = 400, message = "Validation error")
	})
	public ResponseEntity<?> delete(@Valid @NotEmpty @PathVariable String id){
		userService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, 
            					produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully updated User"),
            @ApiResponse(code = 500, message = "A strange error occured"),
            @ApiResponse(code = 400, message = "Validation error")
	})
	public ResponseEntity<?> update(@Valid @NotEmpty @PathVariable String id, 
			                        @Valid @RequestBody NewUserDTO requestDTO){
		UserDomain newUser = userService.fromDTO(requestDTO);
		newUser.setId(id);
		userService.update(newUser);
		
		return ResponseEntity.noContent().build();
	}
}
