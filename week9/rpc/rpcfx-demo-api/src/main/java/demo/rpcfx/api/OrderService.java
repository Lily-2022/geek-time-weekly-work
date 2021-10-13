package demo.rpcfx.api;

import demo.rpcfx.annotation.RemoteApi;

@RemoteApi(url = "http://localhost:8080/order")
public interface OrderService {

    Order findOrderById(int id);

}
