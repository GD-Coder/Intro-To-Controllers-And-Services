package com.cooksys.friendlr.mapper;

import org.mapstruct.Mapper;

import com.cooksys.friendlr.pet.Pet;
import com.cooksys.friendlr.pet.PetDto;
import com.cooksys.friendlr.pet.PetNoIdDto;

@Mapper(componentModel = "spring")
public interface PetMapper {
	
	PetNoIdDto petNoIdDto(Pet p);
	
	Pet toPet(PetNoIdDto dto);
	
	PetDto petDto(Pet p);
	
	Pet toPet(PetDto pet);

}
