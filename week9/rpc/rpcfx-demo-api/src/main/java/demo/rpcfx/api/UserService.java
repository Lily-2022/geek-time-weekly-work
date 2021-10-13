package demo.rpcfx.api;

import demo.rpcfx.annotation.RemoteApi;

@RemoteApi(url = "http://localhost:8080/user")
public interface UserService {

    User findById(int id);

}
