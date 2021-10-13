package demo.rpcfx.server;

import demo.rpcfx.api.User;
import demo.rpcfx.api.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public User findById(int id) {
        return new User(id, "KK" + System.currentTimeMillis());
    }
}
