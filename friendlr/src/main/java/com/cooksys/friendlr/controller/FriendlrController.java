package com.cooksys.friendlr.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cooksys.friendlr.controller.FriendlrService;

import com.cooksys.friendlr.mapper.PersonMapper;
import com.cooksys.friendlr.mapper.PetMapper;
import com.cooksys.friendlr.person.PersonDto;
import com.cooksys.friendlr.person.PersonNoIdDto;
import com.cooksys.friendlr.pet.Pet;
import com.cooksys.friendlr.pet.PetDto;
import com.cooksys.friendlr.pet.PetNoIdDto;
import com.cooksys.friendlr.person.Person;

@RestController
@RequestMapping("friendlr")
public class FriendlrController {

	private FriendlrService service;
	private PersonMapper mapper;
	private PetMapper papper;

	public FriendlrController(FriendlrService service, PersonMapper mapper, PetMapper papper) {
		this.service = service;	
		this.mapper = mapper;
		this.papper = papper;
	}
	
	@GetMapping("person")
	public List<PersonDto> getAll() {
		return service.getAllPersons().stream().map(person -> mapper.toPersonWithId(person)).collect(Collectors.toList());
	}
	
	@GetMapping("person/{id}")
	public PersonNoIdDto get(@PathVariable Integer id) {
		return mapper.toPersonSansId(service.getPerson(id));
	}
	
	@PostMapping("person")
	public Person post(@RequestBody PersonNoIdDto dto, HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_CREATED);
		return service.createPerson(mapper.toPerson(dto));
	}
	
	@PostMapping("person/{personId}/friends/{friendId}")
	public PersonDto post(@PathVariable Integer personId, @PathVariable Integer friendId, HttpServletResponse response) {
		return mapper.toPersonWithId(service.addFriend(personId, friendId));
	}

	@GetMapping("person/{personId}/friends")
	public List<PersonDto> getFriends(@PathVariable Integer personId) {
		return service.getFriends(personId).stream().map(person -> mapper.toPersonWithId(person)).collect(Collectors.toList());
	}
	
	// Pets
	@GetMapping("pets")
	public List<PetDto> getAllpets() {
		return service.getAllPets().stream().map(pet -> papper.petDto(pet)).collect(Collectors.toList());
	}
	
	@PostMapping("pet")
	public Pet post(@RequestBody PetNoIdDto dto, HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_CREATED);
		return service.createPet(papper.toPet(dto));
	}
	@GetMapping("pet/{id}")
	public PetDto getPet(@PathVariable Integer id) {
		return papper.petDto(service.getPet(id));
	}
	@PostMapping("pet/{petId}/owners/{ownerId}")
	public PetDto postPet(@PathVariable Integer petId, @PathVariable Integer ownerId, HttpServletResponse response) {
		return papper.petDto(service.addPet(petId, ownerId));
	}
	@GetMapping("pet/{petId}/owners")
	public List<PersonDto> getOwners(@PathVariable Integer petId) {
		return service.getAllPets().get(petId).getOwners().stream().map(personId -> mapper.toPersonWithId(service.getPerson(personId))).collect(Collectors.toList());
	}
}
