package pe.gob.sunat.microservices.curso.customers.service;

import java.util.Optional;

import pe.gob.sunat.microservices.curso.customers.client.OrderServiceClient;
import pe.gob.sunat.microservices.curso.customers.dao.AddressDaoImpl;
import pe.gob.sunat.microservices.curso.customers.dao.CustomerDaoImpl;
import pe.gob.sunat.microservices.curso.customers.model.Customer;
import pe.gob.sunat.microservices.curso.customers.service.command.OrderServiceRemoteInvokerCommand;

public class CustomerService {
	private final CustomerDaoImpl dao;
	private final AddressDaoImpl addressDao;
	private final OrderServiceClient orderServiceClient;

	public CustomerService(CustomerDaoImpl dao, AddressDaoImpl addressDao, OrderServiceClient orderServiceClient) {
		this.dao = dao;
		this.addressDao = addressDao;
		this.orderServiceClient = orderServiceClient;
	}

	public Customer create(Customer customer) {
		return dao.create(customer);
	}

	public Optional<Customer> findById(Long id, Boolean includeAddresses) {
		return dao.find(id).map(customer -> {
			if (includeAddresses) {
				customer.setAddresses(addressDao.findByCustomer(id));
			}
			return customer;
		});
	}

	public void delete(Long id) {
		if (!customerHaveOrders(id)) {
			dao.delete(id);
		}
	}

	private Boolean customerHaveOrders(Long id) {
		return new OrderServiceRemoteInvokerCommand(orderServiceClient, id).execute();
	}
}
