package pl.chemik77.spring.addressbook.dao;

import java.time.LocalDate;
import java.util.List;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import pl.chemik77.spring.addressbook.model.Person;

public class PersonDaoImplTest extends EntityDaoImplTest {

	@Autowired
	PersonDao personDao;

	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new FlatXmlDataSetBuilder()
				.build(this.getClass().getClassLoader().getResourceAsStream("Data.xml"));
		return dataSet;
	}

	@Test
	public void findById() {
		System.out.println("@Test : findById");
		Assert.assertNotNull(personDao.findById(1));
		Assert.assertNotNull(personDao.findById(2));
		Assert.assertNull(personDao.findById(3));
	}

	@Test
	public void savePerson() {
		System.out.println("@Test : savePerson");
		personDao.savePerson(getSamplePerson());
		List<Person> persons = personDao.findAllPersons();
		for (Person person : persons) {
			System.out.println(person);
		}
		Assert.assertEquals(persons.size(), 3);
	}

	@Test
	public void deletePersonById() {
		System.out.println("@Test : deletePersonById");
		personDao.deletePersonById(1);
		Assert.assertEquals(personDao.findAllPersons().size(), 1);
	}

	@Test
	public void findAllPersons() {
		System.out.println("@Test : findAllPersons");
		List<Person> persons = personDao.findAllPersons();
		for (Person person : persons) {
			System.out.println(person);
		}
		Assert.assertEquals(persons.size(), 2);
	}

	private Person getSamplePerson() {
		Person person = new Person();
		person.setFirstName("Jan");
		person.setLastName("Kowalski");
		person.setEmail("kowalski_jan@gmail.com");
		person.setPhone("502 001 002");
		person.setBirthDate(LocalDate.parse("1990-12-04"));
		// Address address = new Address();
		// address.setStreet("Francuska");
		// address.setHouseNo("1");
		// address.setZipCode("02-541");
		// address.setCity("Warszawa");
		// person.setAddress(address);
		// Group group = new Group();
		// group.setName("Family");
		// List<Person> persons = new ArrayList<>();
		// persons.add(person);
		// group.setPersons(persons);
		// List<Group> groups = new ArrayList<>();
		// groups.add(group);
		// person.setGroups(groups);
		return person;
	}
}
