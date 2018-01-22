package pl.chemik77.spring.addressbook.converter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

import pl.chemik77.spring.addressbook.model.Group;
import pl.chemik77.spring.addressbook.service.GroupService;

@Service
public class GroupFormatter implements Formatter<Group> {

	@Autowired
	GroupService groupService;

	@Override
	public String print(Group object, Locale locale) {
		return (object != null ? object.getId().toString() : "");
	}

	@Override
	public Group parse(String text, Locale locale) throws ParseException {
		int id = Integer.valueOf(text);
		return this.groupService.findById(id);
	}

}
