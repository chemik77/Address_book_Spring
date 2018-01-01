package pl.chemik77.spring.addressbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.chemik77.spring.addressbook.dao.PersonDao;
import pl.chemik77.spring.addressbook.model.Person;

@Service("personService")
@Transactional
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDao dao;

	@Override
	public Person findById(int id) {
		return dao.findById(id);
	}

	@Override
	public void savePerson(Person person) {
		dao.savePerson(person);
	}

	@Override
	public void updatePerson(Person person) {
		Person entity = findById(person.getId());
		if (entity != null) {
			entity.setFirstName(person.getFirstName());
			entity.setLastName(person.getLastName());
			entity.setEmail(person.getEmail());
			entity.setPhone(person.getPhone());
			entity.setBirthDate(person.getBirthDate());
			entity.setAddress(person.getAddress());
			entity.setGroups(person.getGroups());
		}
	}

	@Override
	public void deletePersonById(int id) {
		dao.deletePersonById(id);
	}

	@Override
	public List<Person> findAllPersons() {
		return dao.findAllPersons();
	}

}
