package pe.gob.sunat.microservices.curso.customers.service.command;

import java.util.List;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

import pe.gob.sunat.microservices.curso.customers.client.Order;
import pe.gob.sunat.microservices.curso.customers.client.OrderServiceClient;
import retrofit2.Response;

public class OrderServiceRemoteInvokerCommand extends HystrixCommand<Boolean> {
  public static final int CONCURRENT_REQUESTS = 20;
  private final OrderServiceClient orderServiceClient;
  private final Long id;

  public OrderServiceRemoteInvokerCommand(OrderServiceClient orderServiceClient, Long id) {
    super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GroupComandoLatencia"))
      .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
        .withExecutionIsolationSemaphoreMaxConcurrentRequests(CONCURRENT_REQUESTS)));
    this.orderServiceClient = orderServiceClient;
    this.id = id;
  }


  @Override
  protected Boolean run() throws Exception {
	  Response<List<Order>> orders = orderServiceClient.get(id).execute();
    return !orders.body().isEmpty();
  }

  @Override
  protected Boolean getFallback() {
    return false;
  }
}
