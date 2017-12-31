package pl.chemik77.spring.addressbook.dao;

import java.util.List;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import pl.chemik77.spring.addressbook.model.Person;

public class PersonDaoImplTest extends EntityDaoImplTest {

	@Autowired
	PersonDao personDao;

	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new FlatXmlDataSetBuilder()
				.build(this.getClass().getClassLoader().getResourceAsStream("Data.xml"));
		return dataSet;
	}

	@Test
	public void findById() {
		List<Person> persons = personDao.findAllPersons();
		for (Person person : persons) {
			System.out.println(person);
		}

		Assert.assertNotNull(personDao.findById(1));
		Assert.assertNotNull(personDao.findById(2));
		Assert.assertNull(personDao.findById(3));
	}
}
