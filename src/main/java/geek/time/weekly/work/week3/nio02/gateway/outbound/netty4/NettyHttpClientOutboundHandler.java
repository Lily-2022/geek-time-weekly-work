package geek.time.weekly.work.week3.nio02.gateway.outbound.netty4;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.SocketAddress;

public class NettyHttpClientOutboundHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(NettyHttpClientOutboundHandler.class);

    private NettyHttpClientOutboundHandler(){}

    private static NettyHttpClientOutboundHandler INSTANCE = null;

    public static synchronized NettyHttpClientOutboundHandler getInstance () {
        if (INSTANCE == null) {
            INSTANCE = new NettyHttpClientOutboundHandler();
        }
        return INSTANCE;
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ctx.write(msg);
    }

    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel ch = ctx.channel();
        SocketAddress local = ch.localAddress();
        SocketAddress remote = ch.remoteAddress();
        logger.info(String.format("server(%s) diconnect and client(%s) close connect", remote.toString().substring(1), local.toString().substring(1)));
        ctx.close();
    }

    /*@Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }*/
}
