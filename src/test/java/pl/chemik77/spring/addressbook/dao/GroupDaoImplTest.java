package pl.chemik77.spring.addressbook.dao;

import java.util.List;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import pl.chemik77.spring.addressbook.model.Group;

public class GroupDaoImplTest extends EntityDaoImplTest {

	@Autowired
	GroupDao groupDao;

	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new FlatXmlDataSetBuilder()
				.build(this.getClass().getClassLoader().getResourceAsStream("Data.xml"));
		return dataSet;
	}

	@Test
	public void findAll() {
		System.out.println("@Test : findAll");
		List<Group> groups = groupDao.findAll();
		for (Group group : groups) {
			System.out.println(group);
		}
		Assert.assertEquals(groups.size(), 2);
	}

	@Test
	public void findByName() {
		System.out.println("@Test : findByName");
		Assert.assertNotNull(groupDao.findByName("Friends"));
		Assert.assertNull(groupDao.findByName("Coworkers"));
	}

	@Test
	public void findById() {
		System.out.println("@Test : findById");
		Assert.assertNotNull(groupDao.findById(1));
		Assert.assertNotNull(groupDao.findById(2));
		Assert.assertNull(groupDao.findById(3));
	}

	@Test
	public void saveGroup() {
		System.out.println("@Test : saveGroup");
		groupDao.saveGroup(getSampleGroup());
		List<Group> groups = groupDao.findAll();
		for (Group group : groups) {
			System.out.println(group);
		}
		Assert.assertEquals(groups.size(), 3);
	}

	@Test
	public void deleteById() {
		System.out.println("@Test : deleteById");
		groupDao.deleteById(1);
		Assert.assertEquals(groupDao.findAll().size(), 1);
	}

	@Test
	public void deleteByName() {
		System.out.println("@Test : deleteByName");
		groupDao.deleteByName("Friends");
		Assert.assertEquals(groupDao.findAll().size(), 1);
	}

	private Group getSampleGroup() {
		Group group = new Group();
		group.setName("TestGroup");
		// Person person = new Person();
		// List<Group> groups = new ArrayList<>();
		// List<Person> persons = new ArrayList<>();
		// groups.add(group);
		// persons.add(person);
		// group.setPersons(persons);
		// person.setGroups(groups);
		return group;
	}
}
