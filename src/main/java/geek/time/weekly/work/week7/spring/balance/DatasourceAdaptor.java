package geek.time.weekly.work.week7.spring.balance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DatasourceAdaptor {

    @Autowired
    @Qualifier("master")
    DataSource masterDataSource;

    @Autowired
    @Qualifier("slave")
    DataSource slaveDataSource;

    int slaveIndex = 2;

    public DataSource getDefaultDataSource() {
        return masterDataSource;
    }

    public DataSource getSlaveDataSource() {
        if (slaveIndex == 1) {
            slaveIndex = 2;
            return slaveDataSource;
        }
        slaveIndex = 1;
        return masterDataSource;
    }


}
