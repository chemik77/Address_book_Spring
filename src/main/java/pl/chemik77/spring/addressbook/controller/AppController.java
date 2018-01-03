package pl.chemik77.spring.addressbook.controller;

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
		model.addAttribute("persons", persons);
		return "personslist";
	}
}
