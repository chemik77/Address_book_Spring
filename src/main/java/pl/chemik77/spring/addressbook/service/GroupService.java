package pl.chemik77.spring.addressbook.service;

import java.util.List;

import pl.chemik77.spring.addressbook.model.Group;

public interface GroupService {

	Group findById(int id);

	Group findByName(String name);

	List<Group> findAll();

	void saveGroup(Group group);

	void deleteById(int id);

	void deleteByName(String name);

}