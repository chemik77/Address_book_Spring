package pl.chemik77.spring.addressbook.service;

import java.util.List;

import pl.chemik77.spring.addressbook.model.Person;

public interface PersonService {

	Person findById(int id);

	void savePerson(Person person);

	void updatePerson(Person person);

	void deletePersonById(int id);

	List<Person> findAllPersons();

}
