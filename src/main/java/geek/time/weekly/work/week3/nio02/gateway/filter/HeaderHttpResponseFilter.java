package geek.time.weekly.work.week3.nio02.gateway.filter;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public class HeaderHttpResponseFilter implements HttpResponseFilter{
    @Override
    public void filter(FullHttpResponse response, FullHttpRequest request) {
        response.headers().set("test", "java-1-nio");
        String requestId = request.headers().get("X-Request-ID");
        if (StringUtils.isNotEmpty(requestId)) {
            Thread.currentThread().setName(requestId);
            response.headers().set("X-Request-ID", requestId);
        } else {
            String randomUUID = UUID.randomUUID().toString();
            Thread.currentThread().setName(randomUUID);
            response.headers().set("X-Request-ID", randomUUID);
        }
    }
}
