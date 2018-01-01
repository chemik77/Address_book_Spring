package pl.chemik77.spring.addressbook.dao;

import java.util.List;

import pl.chemik77.spring.addressbook.model.Group;

public interface GroupDao {

	List<Group> findAll();

	Group findByName(String name);

	Group findById(int id);

	void saveGroup(Group group);

	void deleteById(int id);

	void deleteByName(String name);
}
