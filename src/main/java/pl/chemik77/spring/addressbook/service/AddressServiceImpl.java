package pl.chemik77.spring.addressbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.chemik77.spring.addressbook.dao.AddressDao;
import pl.chemik77.spring.addressbook.model.Address;

@Service("addressService")
@Transactional
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressDao dao;

	@Override
	public Address findById(int id) {
		return dao.findById(id);
	}

	@Override
	public void saveAddress(Address address) {
		dao.saveAddress(address);
	}

	@Override
	public void updateAddress(Address address) {
		Address entity = findById(address.getId());
		if (entity != null) {
			entity.setStreet(address.getStreet());
			entity.setHouseNo(address.getHouseNo());
			entity.setZipCode(address.getZipCode());
			entity.setCity(address.getCity());
		}
	}

	@Override
	public void deleteById(int id) {
		dao.deleteById(id);
	}

}
