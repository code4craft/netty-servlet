package us.codecraft.netty_servlet.connector;

import us.codecraft.netty_servlet.container.Dispatcher;

/**
 * @author yihua.huang@dianping.com
 */
public interface Connector {

	public Connector port(int port);

	public Connector stop() throws Exception;

	public Connector start() throws Exception;

    public Connector dispatcher(Dispatcher dispatcher);

}
