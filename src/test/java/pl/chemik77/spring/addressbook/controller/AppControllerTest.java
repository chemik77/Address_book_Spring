package pl.chemik77.spring.addressbook.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
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

import pl.chemik77.spring.addressbook.model.Address;
import pl.chemik77.spring.addressbook.model.Person;
import pl.chemik77.spring.addressbook.service.AddressService;
import pl.chemik77.spring.addressbook.service.PersonService;

public class AppControllerTest {

	@Mock
	PersonService personService;

	@Mock
	AddressService addressService;

	@InjectMocks
	AppController appController;

	@Spy
	List<Person> persons = new ArrayList<>();

	@Spy
	List<Address> addresses = new ArrayList<>();

	@Spy
	ModelMap model;

	@Mock
	BindingResult result;

	@BeforeClass
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		addresses = getAddressesList();
		persons = getPersonsList();
	}

	@Test
	public void listPersons() {
		when(personService.findAllPersons()).thenReturn(persons);
		Assert.assertEquals(appController.listPersons(model), "personslist");
		Assert.assertEquals(model.get("persons"), persons);
		verify(personService, atLeastOnce()).findAllPersons();
	}

	@Test
	public void newPerson() {
		Assert.assertEquals(appController.newPerson(model), "newperson");
		Assert.assertNotNull(model.get("person"));
		Assert.assertNotNull(model.get("address"));
		Assert.assertFalse((Boolean) model.get("edit"));
	}

	@Test
	public void savePersonWithValidationError() {
		when(result.hasErrors()).thenReturn(true);
		doNothing().when(personService).savePerson(any(Person.class));
		Assert.assertEquals(appController.savePerson(persons.get(0), addresses.get(0), result, model), "newperson");
	}

	@Test
	public void savePersonWithSuccess() {
		when(result.hasErrors()).thenReturn(false);
		doNothing().when(addressService).saveAddress(any(Address.class));
		doNothing().when(personService).savePerson(any(Person.class));
		Assert.assertEquals(appController.savePerson(persons.get(0), addresses.get(0), result, model), "success");
		Assert.assertEquals(model.get("success"), "Person TestFirstName1 TestLastName1 registered successfully");
	}

	// edit person
	@Test
	public void editPerson() {
		Person person = persons.get(0);
		Address address = addresses.get(0);
		when(addressService.findById(anyInt())).thenReturn(address);
		when(personService.findById(anyInt())).thenReturn(person);
		Assert.assertEquals(appController.editPerson(anyInt(), model), "editperson");
		Assert.assertNotNull(model.get("person"));
		Assert.assertNotNull(model.get("address"));
		Assert.assertTrue((Boolean) model.get("edit"));
	}

	// update person with validation error
	@Test
	public void updatePersonWithValidationError() {
		when(result.hasErrors()).thenReturn(true);
		doNothing().when(personService).updatePerson(any(Person.class));
		Assert.assertEquals(appController.updatePerson(persons.get(0), addresses.get(0), result, model, 0),
				"editperson");
	}

	// update person with success
	@Test
	public void updatePersonWithSuccess() {
		when(result.hasErrors()).thenReturn(false);
		doNothing().when(addressService).updateAddress(any(Address.class));
		doNothing().when(personService).updatePerson(any(Person.class));
		Assert.assertEquals(appController.updatePerson(persons.get(0), addresses.get(0), result, model, 0), "success");
		Assert.assertEquals(model.get("success"), "Person TestFirstName1 TestLastName1 updated successfully");
	}

	// delete person
	@Test
	public void deletePerson() {
		doNothing().when(personService).deletePersonById(anyInt());
		Assert.assertEquals(appController.deletePerson(99), "redirect:/list");
	}

	private List<Person> getPersonsList() {
		Person p1 = new Person();
		p1.setId(1);
		p1.setFirstName("TestFirstName1");
		p1.setLastName("TestLastName1");
		p1.setEmail("TestEmail1");
		p1.setPhone("TestPhone1");
		p1.setBirthDate(LocalDate.parse("2001-01-01"));
		p1.setAddress(addresses.get(0));

		Person p2 = new Person();
		p2.setId(2);
		p2.setFirstName("TestFirstName2");
		p2.setLastName("TestLastName2");
		p2.setEmail("TestEmail2");
		p2.setPhone("TestPhone2");
		p2.setBirthDate(LocalDate.parse("2001-02-02"));
		p2.setAddress(addresses.get(1));

		persons.add(p1);
		persons.add(p2);
		return persons;
	}

	private List<Address> getAddressesList() {
		Address a1 = new Address();
		a1.setId(1);
		a1.setStreet("StreetTest1");
		a1.setHouseNo("HouseNoTest1");
		a1.setCity("CityTest1");
		a1.setZipCode("ZipCodeTest1");

		Address a2 = new Address();
		a2.setId(2);
		a2.setStreet("StreetTest2");
		a2.setHouseNo("HouseNoTest2");
		a2.setCity("CityTest2");
		a2.setZipCode("ZipCodeTest2");

		addresses.add(a1);
		addresses.add(a2);
		return addresses;
	}
}
