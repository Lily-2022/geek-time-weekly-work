package demo.hmily.sql;

import demo.hmily.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccountMapper {

    @Update("update `hmily_dubbo_account` set usdNum = usdNum + #{usdNum}, cnyNum = cnyNum + " +
            "#{cnyNum} where usdNum >= ${usdNum} and cnyNum >= ${cnyNum} and id = #{id}")
    boolean payment(Account account);

    @Select("select * from hmily_dubbo_account where id = #{id}")
    Account queryById(Account account);

}
