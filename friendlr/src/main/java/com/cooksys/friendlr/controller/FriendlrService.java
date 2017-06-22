package com.cooksys.friendlr.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cooksys.friendlr.person.Person;
import com.cooksys.friendlr.person.PersonDto;
import com.cooksys.friendlr.pet.Pet;

@Service
public class FriendlrService {
	
	List<Person> allPeople = new ArrayList<Person>();
	List<Pet> allPets = new ArrayList<Pet>();	
	List<Person> owners = new ArrayList<Person>();
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	// People
	public List<Person> getAllPersons() {
		return allPeople;
	}

	public Person getPerson(Integer id) {
		return allPeople.get(id);
	}

	public Person createPerson(Person person) {
		person.setId(allPeople.size());
		allPeople.add(person);
		return person;
	}

	public Person addFriend(Integer personId, Integer friendId) {
		if(personId >= allPeople.size() || friendId >= allPeople.size())
			throw new RuntimeException("That person does not exist!");
		
		allPeople.get(personId).getFriends().add(allPeople.get(friendId));
		return allPeople.get(personId);
	}

	public List<Person> getFriends(Integer personId) {
		return allPeople.get(personId).getFriends();
	}
	
	// Pets
	public List<Pet> getAllPets() {
		return allPets;
	}

	public Pet getPet(Integer id) {
		return allPets.get(id);
	}
	public Pet createPet(Pet pet) {
		pet.setId(Long.valueOf(allPets.size()));
		allPets.add(pet);
		return pet;
	}
	public List<Integer> getPetOwners(Integer petId) {		
		return allPets.get(petId).getOwners();
	}
	public Pet addPet(Integer petId, Integer ownerId) {		
		allPeople.get(ownerId).getPets().add(petId);
		if(allPets.contains(ownerId)) {
			return allPets.get(petId);
		}
		else {
		allPets.get(petId).getOwners().add(ownerId);
		return allPets.get(petId);
		}
	}

}
