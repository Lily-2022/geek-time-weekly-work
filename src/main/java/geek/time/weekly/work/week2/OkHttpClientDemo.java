package geek.time.weekly.work.week2;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OkHttpClientDemo {

    private static final Logger logger = LoggerFactory.getLogger(OkHttpClientDemo.class);

    public static void main(String[] args) {
        OkHttpClient httpClient = new OkHttpClient();

        String response = retrieveData(httpClient);
        if (StringUtils.isNotEmpty(response)) {
            logger.info("Response from http server is: {}", response);
        } else {
            logger.warn("No data from http server.");
        }
    }

    private static String retrieveData(OkHttpClient httpClient) {
        String url = "http://localhost:8801";
        try (Response response = httpClient.newCall(new Request.Builder()
                .url(url)
                .get()
                .build()).execute()) {
            logger.info("response code: {} from url: {}", response.code(), url);
            if (response.isSuccessful()) {
                return response.body().string();
            }

        } catch (Exception e) {
            logger.warn("Exception happened when request to server.", e);
        }
        return null;
    }


}
