package pl.chemik77.spring.addressbook.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.chemik77.spring.addressbook.model.*;
import pl.chemik77.spring.addressbook.service.AddressService;
import pl.chemik77.spring.addressbook.service.PersonService;

@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	PersonService personService;

	@Autowired
	AddressService addressService;

	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listPersons(ModelMap model) {
		List<Person> persons = personService.findAllPersons();
		if (persons.isEmpty()) {
			persons = getPersonsList();
		}
		model.addAttribute("persons", persons);
		return "personslist";
	}

	private List<Person> getPersonsList() {
		Person person = new Person();
		person.setId(1);
		person.setFirstName("Jan");
		person.setLastName("Kowalski");
		person.setEmail("kowalski_jan@gmail.com");
		person.setPhone("502 001 002");
		person.setBirthDate(LocalDate.parse("1990-12-04"));
		Address address = new Address();
		address.setStreet("Jesionowa");
		address.setHouseNo("1/5");
		address.setCity("Warszawa");
		address.setZipCode("00-254");
		person.setAddress(address);
		List<Person> persons = new ArrayList<Person>();
		persons.add(person);
		return persons;
	}

	@RequestMapping(value = "/newperson", method = RequestMethod.GET)
	public String newPerson(ModelMap model) {
		Person person = new Person();
		Address address = new Address();

		model.addAttribute("person", person);
		model.addAttribute("address", address);
		model.addAttribute("edit", false);
		return "newperson";
	}

	@RequestMapping(value = "/newperson", method = RequestMethod.POST)
	public String savePerson(@Valid Person person, @Valid Address address, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "newperson";
		}
		person.setAddress(address);
		addressService.saveAddress(address);
		personService.savePerson(person);
		model.addAttribute("success",
				"Person " + person.getFirstName() + " " + person.getLastName() + " registered successfully");
		return "success";
	}

	@RequestMapping(value = "/edit-{id}", method = RequestMethod.GET)
	public String editPerson(@PathVariable int id, ModelMap model) {
		Person person = personService.findById(id);
		// Address address =
		// addressService.findById(person.getAddress().getId());
		Address address = null;
		if (person.getAddress() != null) {
			address = addressService.findById(person.getAddress().getId());
		} else {
			address = new Address();
		}

		model.addAttribute("person", person);
		model.addAttribute("address", address);
		model.addAttribute("edit", true);
		return "editperson";
	}

	@RequestMapping(value = "/edit-{id}", method = RequestMethod.POST)
	public String updatePerson(@Valid Person person, @Valid Address address, BindingResult result, ModelMap model,
			@PathVariable int id) {
		if (result.hasErrors()) {
			return "editperson";
		}
		person.setAddress(address);
		addressService.updateAddress(address);
		personService.updatePerson(person);
		model.addAttribute("success",
				"Person " + person.getFirstName() + " " + person.getLastName() + " updated successfully");
		return "success";
	}

	@RequestMapping(value = "/delete-{id}", method = RequestMethod.GET)
	public String deletePerson(@PathVariable int id) {
		personService.deletePersonById(id);
		return "redirect:/list";
	}
}
