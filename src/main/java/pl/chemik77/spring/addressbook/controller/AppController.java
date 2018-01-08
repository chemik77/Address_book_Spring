package pl.chemik77.spring.addressbook.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.chemik77.spring.addressbook.model.Person;
import pl.chemik77.spring.addressbook.service.PersonService;

@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	PersonService personService;

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
		List<Person> persons = new ArrayList<Person>();
		persons.add(person);
		return persons;
	}

	@RequestMapping(value = "/newperson", method = RequestMethod.GET)
	public String newPerson() {

		return "newperson";
	}

	@RequestMapping(value = "/newperson", method = RequestMethod.POST)
	public String savePerson() {

		return "success";
	}

	@RequestMapping(value = "/edit-{id}", method = RequestMethod.GET)
	public String editPerson() {

		return "editperson";
	}

	@RequestMapping(value = "/edit-{id}", method = RequestMethod.POST)
	public String updatePerson() {

		return "success";
	}

	@RequestMapping(value = "/delete-{id}", method = RequestMethod.GET)
	public String deletePerson() {

		return "redirect:/list";
	}
}
