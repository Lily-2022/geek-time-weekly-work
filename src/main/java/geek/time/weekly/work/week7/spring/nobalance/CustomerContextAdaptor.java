package geek.time.weekly.work.week7.spring.nobalance;

public class CustomerContextAdaptor {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setCustomerType(String customerType) {
        contextHolder.set(customerType);
    }

    public static String getCustomerType() {
        return contextHolder.get();
    }

    public static void clear() {
        contextHolder.remove();
    }

}
