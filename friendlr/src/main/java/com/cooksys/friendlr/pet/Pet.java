package com.cooksys.friendlr.pet;

import java.util.ArrayList;
import java.util.List;

import com.cooksys.friendlr.person.Person;

public class Pet {
	
	private String name;
	private Long id;
	private List<Integer> owners = new ArrayList<Integer>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Integer> getOwners() {
		return owners;
	}
	public void setOwners(List<Integer> owners) {
		this.owners = owners;
	}
	public void setOwner(List<Integer> owner) {
		this.owners = owner;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((owners == null) ? 0 : owners.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pet other = (Pet) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (owners == null) {
			if (other.owners != null)
				return false;
		} else if (!owners.equals(other.owners))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Pet [name=" + name + ", id=" + id + ", owners=" + owners + "]";
	}
	
	
}
