package demo.rpcfx.server;


import demo.rpcfx.api.OrderService;
import demo.rpcfx.api.UserService;
import demo.rpcfx.core.api.RpcfxRequest;
import demo.rpcfx.core.api.RpcfxResolver;
import demo.rpcfx.core.api.RpcfxResponse;
import demo.rpcfx.core.server.RpcfxInvoker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RpcfxServerApplication {

	public static void main(String[] args) throws Exception {

//		// start zk client
//		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
//		CuratorFramework client = CuratorFrameworkFactory.builder().connectString("localhost:2181").namespace("rpcfx").retryPolicy(retryPolicy).build();
//		client.start();
//
//
//		// register service
//		// xxx "io.kimmking.rpcfx.demo.api.UserService"
//
//		String userService = "io.kimking.rpcfx.demo.api.UserService";
//		registerService(client, userService);
//		String orderService = "io.kimking.rpcfx.demo.api.OrderService";
//		registerService(client, orderService);


		// 进一步的优化，是在spring加载完成后，从里面拿到特定注解的bean，自动注册到zk

		SpringApplication.run(RpcfxServerApplication.class, args);
	}

//	private static void registerService(CuratorFramework client, String service) throws Exception {
//		ServiceProviderDesc userServiceSesc = ServiceProviderDesc.builder()
//				.host(InetAddress.getLocalHost().getHostAddress())
//				.port(8080).serviceClass(service).build();
//		// String userServiceSescJson = JSON.toJSONString(userServiceSesc);
//
//		try {
//			if ( null == client.checkExists().forPath("/" + service)) {
//				client.create().withMode(CreateMode.PERSISTENT).forPath("/" + service, "service".getBytes());
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//
//		client.create().withMode(CreateMode.EPHEMERAL).
//				forPath( "/" + service + "/" + userServiceSesc.getHost() + "_" + userServiceSesc.getPort(), "provider".getBytes());
//	}

	@Autowired
	RpcfxInvoker invoker;

	@PostMapping("/")
	public RpcfxResponse invoke(@RequestBody RpcfxRequest request) {
		return invoker.invoke(request);
	}

	@Bean
	public RpcfxInvoker createInvoker(@Autowired RpcfxResolver resolver){
		return new RpcfxInvoker(resolver);
	}

	@Bean
	public RpcfxResolver createResolver(){
		return new DemoResolver();
	}

	// 能否去掉name
	//

	// annotation


	@Bean(name = "demo.rpcfx.api.UserService")
	public UserService createUserService(){
		return new UserServiceImpl();
	}

	@Bean(name = "demo.rpcfx.api.OrderService")
	public OrderService createOrderService(){
		return new OrderServiceImpl();
	}

}
