package pe.gob.sunat.microservices.curso.customers.service;

import java.util.List;
import java.util.Optional;

import pe.gob.sunat.microservices.curso.customers.dao.AddressDaoImpl;
import pe.gob.sunat.microservices.curso.customers.model.Address;

public class AddressService {
	private final AddressDaoImpl dao;

	public AddressService(AddressDaoImpl dao) {
		this.dao = dao;
	}

	public Address create(Long customerId, Address address) {
		return dao.create(customerId, address);
	}

	public List<Address> addressesByCustomer(Long id) {
		return dao.findByCustomer(id);
	}

	public void delete(Long id) {
		dao.delete(id);
	}

	public Optional<Address> addressesById(Long id) {
		return dao.findById(id);
	}
}
