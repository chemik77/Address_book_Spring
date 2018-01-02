package pl.chemik77.spring.addressbook.service;

import pl.chemik77.spring.addressbook.model.Address;

public interface AddressService {

	Address findById(int id);
	
	void saveAddress(Address address);
	
	void deleteById(int id);
	
}
