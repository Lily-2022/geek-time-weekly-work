package demo.rpcfx.core.http;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpClientFactory {

    private static HttpClientFactory INSTANCE = null;

    private HttpClientFactory () {}

    public synchronized static HttpClientFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HttpClientFactory();
        }
        return INSTANCE;
    }

    public synchronized HttpClient getHttpClient() {
        HttpClient httpClient = HttpClientBuilder.create().build();
        return httpClient;
    }

    public HttpPost httpPost (String url) {
        return new HttpPost(url);
    }

}
