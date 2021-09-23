package geek.time.weekly.work.week7.spring;

import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DatasourceAdaptor {

    DataSource masterDataSource;
    DataSource slaveDataSource;

    int slaveIndex = 1;

    public DataSource getDefaultDataSource () {
        return masterDataSource;
    }

    public DataSource getSlaveDataSource () {
        if (slaveIndex == 1) {
            slaveIndex = 2;
            return slaveDataSource;
        }
        slaveIndex = 1;
        return slaveDataSource;
    }


}
