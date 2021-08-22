package geek.time.weekly.work.week3.nio02.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public class HeaderHttpRequestFilter implements HttpRequestFilter{
    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        fullRequest.headers().set("mao", "soul");
        String requestId = fullRequest.headers().get("X-Request-ID");
        if (StringUtils.isNotEmpty(requestId)) {
            Thread.currentThread().setName(requestId);
        } else {
            String randomUUID = UUID.randomUUID().toString();
            Thread.currentThread().setName(randomUUID);
            fullRequest.headers().set("X-Request-ID", randomUUID);
        }
    }
}
