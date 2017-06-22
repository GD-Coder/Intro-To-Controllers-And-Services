package com.cooksys.friendlr.mapper;

import org.mapstruct.Mapper;

import com.cooksys.friendlr.person.PersonDto;
import com.cooksys.friendlr.person.PersonNoIdDto;
import com.cooksys.friendlr.person.Person;

@Mapper(componentModel = "spring")
public interface PersonMapper {
	
	PersonNoIdDto toPersonSansId(Person p);
	
	Person toPerson(PersonNoIdDto dto);
	
	PersonDto toPersonWithId(Person p);
	
	Person toPerson(PersonDto dto);

}
