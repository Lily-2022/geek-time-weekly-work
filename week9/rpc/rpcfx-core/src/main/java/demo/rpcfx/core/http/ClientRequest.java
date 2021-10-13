package demo.rpcfx.core.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

@Slf4j
public class ClientRequest {

    public static String getRequestMethod(String parameter, String url) {
        try {
            HttpClient httpClient = HttpClientFactory.getInstance().getHttpClient();

            HttpPost httpPost = HttpClientFactory.getInstance().httpPost(url);

            Header header = new BasicHeader("Accept-Encoding", null);
            httpPost.setHeader(header);

            StringEntity stringEntity = new StringEntity(parameter, "UTF-8");
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            log.info("请求{}接口的参数为{}", url, parameter);

            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            String result = EntityUtils.toString(httpEntity);
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
