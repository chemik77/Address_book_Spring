package pl.chemik77.spring.addressbook.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.chemik77.spring.addressbook.model.*;
import pl.chemik77.spring.addressbook.service.AddressService;
import pl.chemik77.spring.addressbook.service.GroupService;
import pl.chemik77.spring.addressbook.service.PersonService;

@Controller
@RequestMapping("/")
public class AppController {

	private static final Logger logger = LoggerFactory.getLogger(AppController.class);
	
	@Autowired
	PersonService personService;

	@Autowired
	AddressService addressService;

	@Autowired
	GroupService groupService;

	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listPersons(ModelMap model) {
		List<Person> persons = personService.findAllPersons();
		model.addAttribute("persons", persons);
		return "personslist";
	}

	@RequestMapping(value = "/newperson", method = RequestMethod.GET)
	public String newPerson(ModelMap model) {
		Person person = new Person();
		Address address = new Address();
		List<Group> groups = initializeGroups();
		model.addAttribute("person", person);
		model.addAttribute("address", address);
		model.addAttribute("groups", groups);
		model.addAttribute("edit", false);
		return "newperson";
	}

	@RequestMapping(value = "/newperson", method = RequestMethod.POST)
	public String savePerson(@Valid Person person, @Valid Address address, @ModelAttribute("groups") List<Group> groups,
			BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "newperson";
		}
		person.setAddress(address);
		addressService.saveAddress(address);
		personService.savePerson(person);
		model.addAttribute("success",
				"Person " + person.getFirstName() + " " + person.getLastName() + " registered successfully");
		logger.debug("Add new person: " + person);
		logger.debug("Add new address: " + address);
		return "success";
	}

	@RequestMapping(value = "/edit-{id}", method = RequestMethod.GET)
	public String editPerson(@PathVariable int id, ModelMap model) {
		Person person = personService.findById(id);
		Address address = addressService.findById(person.getAddress().getId());
		List<Group> allGroups = initializeGroups();
		List<Group> selectedGroups = person.getGroups();
		model.addAttribute("person", person);
		model.addAttribute("address", address);
		model.addAttribute("groups", allGroups);
		model.addAttribute("selectedGroups", selectedGroups);
		model.addAttribute("edit", true);
		logger.debug("Edit person: " + person);
		logger.debug("Edit address: " + address);
		return "editperson";
	}

	@RequestMapping(value = "/edit-{id}", method = RequestMethod.POST)
	public String updatePerson(@Valid Person person, @Valid Address address, @ModelAttribute("groups") List<Group> groups, BindingResult result, ModelMap model,
			@PathVariable int id) {
		if (result.hasErrors()) {
			return "editperson";
		}
		person.setAddress(address);
		addressService.updateAddress(address);
		personService.updatePerson(person);
		model.addAttribute("success",
				"Person " + person.getFirstName() + " " + person.getLastName() + " updated successfully");
		logger.debug("Update person: " + person);
		logger.debug("Update address: " + address);
		return "success";
	}

	@RequestMapping(value = "/delete-{id}", method = RequestMethod.GET)
	public String deletePerson(@PathVariable int id) {
		personService.deletePersonById(id);
		Person person = personService.findById(id);
		logger.debug("Delete person: " + person);
		return "redirect:/list";
	}

	@RequestMapping(value = "/newgroup", method = RequestMethod.GET)
	public String newGroup(ModelMap model) {
		Group group = new Group();
		model.addAttribute("group", group);
		return "newgroup";
	}

	@RequestMapping(value = "/newgroup", method = RequestMethod.POST)
	public String saveGroup(@Valid Group group, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "newgroup";
		}
		groupService.saveGroup(group);
		logger.debug("Add new group: " + group);
		return "redirect:/newperson";
	}

	public List<Group> initializeGroups() {
		List<Group> groups = groupService.findAll();
		return groups;
	}

}
