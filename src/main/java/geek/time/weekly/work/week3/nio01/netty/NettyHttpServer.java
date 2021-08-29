package geek.time.weekly.work.week3.nio01.netty;

import geek.time.weekly.work.week3.nio02.gateway.outbound.netty4.NettyHttpClient;
import geek.time.weekly.work.week3.nio02.gateway.outbound.netty4.NettyHttpClientOutboundHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyHttpServer {

    private static final Logger logger = LoggerFactory.getLogger(NettyHttpServer.class);

    public static void main(String[] args) throws InterruptedException{
        int port = 8808;

        EventLoopGroup bossGroup = new NioEventLoopGroup(2);
        EventLoopGroup workerGroup = new NioEventLoopGroup(16);

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.SO_REUSEADDR, true)
                    .childOption(ChannelOption.SO_RCVBUF, 32 * 1024)
                    .childOption(ChannelOption.SO_SNDBUF, 32 * 1024)
                    .childOption(EpollChannelOption.SO_REUSEPORT, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

            NettyHttpClient nettyHttpClient = NettyHttpClient.getInstance("127.0.0.1", 8801);
            nettyHttpClient.start();

            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
//                    .childHandler(new HttpInitializer());
                    .childHandler(nettyHttpClient.getChannel().pipeline().last()); //todo use netty client to replace http handler

            Channel ch = b.bind(port).sync().channel();
            logger.info("Start netty http server, listen addr and port is: http://127.0.0.1:" + port + "/");
            ch.closeFuture().sync();
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }



}
