package pl.chemik77.spring.addressbook.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.atLeastOnce;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pl.chemik77.spring.addressbook.model.Person;
import pl.chemik77.spring.addressbook.service.PersonService;

public class AppControllerTest {

	@Mock
	PersonService personService;

	@InjectMocks
	AppController appController;

	@Spy
	List<Person> persons = new ArrayList<>();

	@Spy
	ModelMap model;

	@Mock
	BindingResult result;

	@BeforeClass
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		persons = getPersonsList();
	}

	@Test
	public void listPersons() {
		when(personService.findAllPersons()).thenReturn(persons);
		Assert.assertEquals(appController.listPersons(model), "personslist");
		Assert.assertEquals(model.get("persons"), persons);
		verify(personService, atLeastOnce()).findAllPersons();
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
		p2.setBirthDate(LocalDate.parse("2001-02-02"));

		persons.add(p1);
		persons.add(p2);
		return persons;
	}
}
