package aman.election.entities;

import java.io.Serializable;
import javax.persistence.*;

import aman.election.exceptions.DataInputException;

/**
 * Entity implementation class for Entity: PersonsName
 *
 */

@Embeddable
public class PersonsName implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Column(name="FIRST_NAME")
	private String firstName;
	@Column(name="LAST_NAME")
	private String lastName;

	public PersonsName() {
		super();
	}

	public PersonsName(String firstName, String lastname) throws DataInputException {
		setFirstName(firstName);
		setLastName(lastName);
	}
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) throws DataInputException {
		if (firstName == null || firstName.trim().isEmpty() ) {
			throw new DataInputException( "A person must have a first name");
		}
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) throws DataInputException {
		if (lastName == null || lastName.trim().isEmpty() ) {
			throw new DataInputException( "A person must have a last name");
		}
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return  firstName + " " + lastName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
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
		PersonsName other = (PersonsName) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
	
}
