package pe.gob.sunat.microservices.curso.customers.client;

public class Address {
  private Long id;
  private String street;
  private String name;
  private String country;
  private Long customerId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Address [id=");
	builder.append(id);
	builder.append(", street=");
	builder.append(street);
	builder.append(", name=");
	builder.append(name);
	builder.append(", country=");
	builder.append(country);
	builder.append(", customerId=");
	builder.append(customerId);
	builder.append("]");
	return builder.toString();
}
  
  
}
