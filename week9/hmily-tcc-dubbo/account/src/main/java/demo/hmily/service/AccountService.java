package demo.hmily.service;

import demo.hmily.entity.Account;
import org.dromara.hmily.annotation.Hmily;

public interface AccountService {

    @Hmily
    boolean pay(Account account);

}
