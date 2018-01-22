package pl.chemik77.spring.addressbook.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pl.chemik77.spring.addressbook.dao.AddressDao;
import pl.chemik77.spring.addressbook.model.Address;

public class AddressServiceImplTest {

	@Mock
	AddressDao dao;

	@InjectMocks
	AddressServiceImpl addressService;

	@Spy
	private List<Address> addresses = new ArrayList<>();

	@BeforeMethod
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		addresses = getSampleAddresses();
	}

	@Test
	public void findById() {
		Address address = addresses.get(0);
		when(dao.findById(anyInt())).thenReturn(address);
		Assert.assertEquals(addressService.findById(address.getId()), address);
	}

	@Test
	public void saveAddress() {
		doNothing().when(dao).saveAddress(any(Address.class));
		addressService.saveAddress(any(Address.class));
		verify(dao, atLeastOnce()).saveAddress(any(Address.class));
	}

	@Test
	public void updateAddress() {
		Address address = addresses.get(0);
		when(dao.findById(anyInt())).thenReturn(address);
		addressService.updateAddress(address);
		verify(dao, atLeastOnce()).findById(anyInt());
	}

	@Test
	public void deleteById() {
		doNothing().when(dao).deleteById(anyInt());
		addressService.deleteById(anyInt());
		verify(dao, atLeastOnce()).deleteById(anyInt());
	}

	private List<Address> getSampleAddresses() {
		Address a1 = new Address();
		a1.setId(1);
		a1.setStreet("TestStreet1");
		a1.setHouseNo("TestHouse1");
		a1.setZipCode("TestZipCode1");
		a1.setCity("TestCity1");
		Address a2 = new Address();
		a2.setId(2);
		a2.setStreet("TestStreet2");
		a2.setHouseNo("TestHouse2");
		a2.setZipCode("TestZipCode2");
		a2.setCity("TestCity2");

		addresses.add(a1);
		addresses.add(a2);
		return addresses;
	}
}
