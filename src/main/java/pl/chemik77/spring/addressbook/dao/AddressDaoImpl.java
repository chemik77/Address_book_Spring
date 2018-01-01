package pl.chemik77.spring.addressbook.dao;

import org.springframework.stereotype.Repository;

import pl.chemik77.spring.addressbook.model.Address;

@Repository("addressDao")
public class AddressDaoImpl extends AbstractDao<Integer, Address> implements AddressDao {

	@Override
	public Address findById(int id) {
		Address address = getByKey(id);
		return address;
	}

	@Override
	public void saveAddress(Address address) {
		persist(address);
	}

	@Override
	public void deleteById(int id) {
		Address address = getByKey(id);
		delete(address);
	}

}
