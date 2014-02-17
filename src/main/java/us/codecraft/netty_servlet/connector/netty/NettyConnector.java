package us.codecraft.netty_servlet.connector.netty;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import us.codecraft.netty_servlet.connector.AbstractConnector;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * @author code4crafter@gmail.com
 */
public class NettyConnector extends AbstractConnector {

    private ServerBootstrap bootstrap;

    @Override
    public NettyConnector stop() throws Exception {
        bootstrap.shutdown();
        return this;
    }

    @Override
    public NettyConnector start() throws Exception {
        bootstrap = new ServerBootstrap(
                new NioServerSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()));

        // Set up the event pipeline factory.
        bootstrap.setPipelineFactory(new HttpServerPipelineFactory(getDispatcher()));

        bootstrap.setOption("child.tcpNoDelay", true);

        // Bind and start to accept incoming connections.
        bootstrap.bind(new InetSocketAddress(getPort()));
        return this;
    }
}
