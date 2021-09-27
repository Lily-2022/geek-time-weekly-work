package geek.time.weekly.work.week7.spring.nobalance;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return CustomerContextAdaptor.getCustomerType();
    }
}
