package pl.chemik77.spring.addressbook.dao;

import pl.chemik77.spring.addressbook.model.Address;

public interface AddressDao {

	Address findById(int id);

	void saveAddress(Address address);

	void deleteById(int id);

}
