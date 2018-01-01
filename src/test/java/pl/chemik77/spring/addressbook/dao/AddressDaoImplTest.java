package pl.chemik77.spring.addressbook.dao;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import pl.chemik77.spring.addressbook.model.Address;

public class AddressDaoImplTest extends EntityDaoImplTest {

	@Autowired
	AddressDao addressDao;

	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new FlatXmlDataSetBuilder()
				.build(this.getClass().getClassLoader().getResourceAsStream("Data.xml"));
		return dataSet;
	}

	@Test
	public void findById() {
		System.out.println("@Test : findById");
		Assert.assertNotNull(addressDao.findById(1));
		Assert.assertNotNull(addressDao.findById(2));
		Assert.assertNull(addressDao.findById(3));
	}

	@Test
	public void saveAddress() {
		System.out.println("@Test : saveAddress");
		addressDao.saveAddress(getSampleAddress());
		Assert.assertNotNull(addressDao.findById(3));
	}

	@Test
	public void deleteById() {
		System.out.println("@Test : deleteById");
		addressDao.deleteById(2);
		Assert.assertNull(addressDao.findById(2));
	}

	private Address getSampleAddress() {
		Address address = new Address();
		address.setStreet("TestStreet");
		address.setHouseNo("TestHouseNo");
		address.setZipCode("TestZipCode");
		address.setCity("TestCity");
		return address;
	}
}
