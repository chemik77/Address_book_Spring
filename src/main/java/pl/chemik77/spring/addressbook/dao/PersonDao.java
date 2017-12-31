package pl.chemik77.spring.addressbook.dao;

import java.util.List;

import pl.chemik77.spring.addressbook.model.Person;

public interface PersonDao {

	Person findById(int id);

	List<Person> findByLastName(String lastName);

	void savePerson(Person person);

	void deletePersonById(int id);

	List<Person> findAllPersons();

}
