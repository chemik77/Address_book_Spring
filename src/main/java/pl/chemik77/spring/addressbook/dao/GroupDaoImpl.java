package pl.chemik77.spring.addressbook.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import pl.chemik77.spring.addressbook.model.Group;

@Repository("groupDao")
public class GroupDaoImpl extends AbstractDao<Integer, Group> implements GroupDao {

	@Override
	public List<Group> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Group findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Group findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
