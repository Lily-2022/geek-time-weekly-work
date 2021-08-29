package geek.time.weekly.work.week3.nio02.gateway.outbound.netty4;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyHttpClient {

    private static Logger logger = LoggerFactory.getLogger(NettyHttpClient.class);

    private final String host;
    private final int port;
    private Channel channel;

    private static NettyHttpClient INSTANCE = null;

    public static synchronized NettyHttpClient getInstance (String host, int port) {
        if (INSTANCE == null) {
            INSTANCE = new NettyHttpClient(host, port);
        }
        return INSTANCE;
    }
    private NettyHttpClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        final EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        logger.info("正在连接....");
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(NettyHttpClientOutboundHandler.getInstance());
                    }
                });
        final ChannelFuture future = b.connect(host, port).sync();

        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (future.isSuccess()) {
                    logger.info("连接服务器成功...");
                } else {
                    logger.warn("连接服务器失败...");
                    future.cause().printStackTrace();
                    group.shutdownGracefully();
                }
            }
        });
        this.channel = future.channel();

    }

    public Channel getChannel() {
        return channel;
    }

}
