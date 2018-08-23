package pe.gob.sunat.microservices.curso.customers.api;


import java.util.List;
import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pe.gob.sunat.microservices.curso.customers.model.Address;
import pe.gob.sunat.microservices.curso.customers.service.AddressService;

@Path("/v1/addresses")
@Produces(MediaType.APPLICATION_JSON)
public class AddressResource {

  private final AddressService addressService;

  public AddressResource(AddressService addressService) {
    this.addressService = addressService;
  }

  @GET
  @Path("/_customer/{id}")
  public List<Address> find(@PathParam("id") Long id) {
    return addressService.addressesByCustomer(id);
  }
  
  @GET
  @Path("/{id}")
  public Optional<Address> findById(@PathParam("id") Long id) {
    return addressService.addressesById(id);
  }

}
