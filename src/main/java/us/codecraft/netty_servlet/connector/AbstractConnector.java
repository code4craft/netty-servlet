package us.codecraft.netty_servlet.connector;

import us.codecraft.netty_servlet.container.Dispatcher;

/**
 * @author code4crafter@gmail.com
 */
public abstract class AbstractConnector implements Connector {

	private int port;

    private Dispatcher dispatcher;

	@Override
	public AbstractConnector port(int port) {
		this.port = port;
		return this;
	}

	protected int getPort() {
		return port;
	}

    @Override
    public Connector dispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
        return this;
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }
}
