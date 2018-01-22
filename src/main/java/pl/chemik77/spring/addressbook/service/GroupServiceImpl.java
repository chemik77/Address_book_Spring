package pl.chemik77.spring.addressbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.chemik77.spring.addressbook.dao.GroupDao;
import pl.chemik77.spring.addressbook.model.Group;

@Service("groupService")
@Transactional
public class GroupServiceImpl implements GroupService {

	@Autowired
	GroupDao dao;

	@Override
	public Group findById(int id) {
		return dao.findById(id);
	}

	@Override
	public Group findByName(String name) {
		return dao.findByName(name);
	}

	@Override
	public List<Group> findAll() {
		return dao.findAll();
	}

	@Override
	public void saveGroup(Group group) {
		dao.saveGroup(group);
	}

	@Override
	public void deleteById(int id) {
		dao.deleteById(id);
	}

	@Override
	public void deleteByName(String name) {
		dao.deleteByName(name);
	}

	@Override
	public void initFirstGroups() {
		dao.initFirstGroups();
	}
	
}
