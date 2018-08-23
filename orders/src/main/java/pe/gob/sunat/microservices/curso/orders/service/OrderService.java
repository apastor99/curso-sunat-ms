package pe.gob.sunat.microservices.curso.orders.service;

import java.util.Date;
import java.util.List;

import pe.gob.sunat.microservices.curso.orders.dao.OrderDaoImpl;
import pe.gob.sunat.microservices.curso.orders.model.Order;

public class OrderService {

	private final CustomerService customerService;
	private final AddressService addressService;
	private final OrderDaoImpl dao;

	public OrderService(CustomerService customerService, AddressService addressService, OrderDaoImpl dao) {
		this.customerService = customerService;
		this.dao = dao;
		this.addressService = addressService;
	}

	public Order create(Order order) {
		Boolean validatedCustomer = customerService.validateCustomer(order.getCustomerId());

//		if (!validatedCustomer) {
//			throw new InvalidCustomerException("No se pudo validar al cliente. Se cancela la creación del pedido.",
//					order.getCustomerId().toString());
//		}

		Boolean validatedAddress = addressService.validateAddress(order.getCustomerId(), order.getDeliveryAddressId());

		if (!validatedAddress.booleanValue()) {
			throw new InvalidCustomerException("No se pudo validar la direccion. Se cancela la creación del pedido.",
					order.getCustomerId().toString());
		}
		order.setCreatedAt(new Date());
		return dao.create(order);
	}

	public List<Order> ordersByCustomer(Long id) {
		return dao.findByCustomer(id);
	}

	public void delete(Long id) {
		dao.delete(id);
	}
}
