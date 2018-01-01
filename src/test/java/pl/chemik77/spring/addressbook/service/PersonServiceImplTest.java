package pl.chemik77.spring.addressbook.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pl.chemik77.spring.addressbook.dao.PersonDao;
import pl.chemik77.spring.addressbook.model.Person;

public class PersonServiceImplTest {

	@Mock
	PersonDao dao;

	@InjectMocks
	PersonServiceImpl personService;

	@Spy
	List<Person> persons = new ArrayList<>();

	@BeforeClass
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		persons = getPersonsList();
	}

	@Test
	public void findById() {
		Person person = persons.get(0);
		when(dao.findById(anyInt())).thenReturn(person);
		Assert.assertEquals(personService.findById(person.getId()), person);
	}

	@Test
	public void savePerson() {
		doNothing().when(dao).savePerson(any(Person.class));
		personService.savePerson(any(Person.class));
		verify(dao, atLeastOnce()).savePerson(any(Person.class));
	}

	@Test
	public void updatePerson() {
		Person person = persons.get(0);
		when(dao.findById(anyInt())).thenReturn(person);
		personService.updatePerson(person);
		verify(dao, atLeastOnce()).findById(anyInt());
	}

	@Test
	public void deletePersonById() {
		doNothing().when(dao).deletePersonById(anyInt());
		personService.deletePersonById(anyInt());
		verify(dao, atLeastOnce()).deletePersonById(anyInt());
	}

	@Test
	public void findAllPersons() {
		when(dao.findAllPersons()).thenReturn(persons);
		Assert.assertEquals(personService.findAllPersons(), persons);
	}

	private List<Person> getPersonsList() {
		Person p1 = new Person();
		p1.setId(1);
		p1.setFirstName("TestFirstName1");
		p1.setLastName("TestLastName1");
		p1.setEmail("TestEmail1");
		p1.setPhone("TestPhone1");
		p1.setBirthDate(LocalDate.parse("2001-01-01"));

		Person p2 = new Person();
		p2.setId(2);
		p2.setFirstName("TestFirstName2");
		p2.setLastName("TestLastName2");
		p2.setEmail("TestEmail2");
		p2.setPhone("TestPhone2");
		p2.setBirthDate(LocalDate.parse("2001-01-01"));

		persons.add(p1);
		persons.add(p2);
		return persons;
	}
}
