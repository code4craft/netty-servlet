package us.codecraft.express.connector;

import org.apache.http.client.fluent.Request;
import org.junit.Test;
import us.codecraft.netty_servlet.connector.Connector;
import us.codecraft.netty_servlet.connector.netty.NettyConnector;
import us.codecraft.netty_servlet.container.Dispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author yihua.huang@dianping.com
 */
public class NettyConnectorTest {

	public static final String HELLO_WORLD = "hello_world";
	public static final String TEST_HEADER = "test";

	@Test
	public void should_mock_dispatcher_return_assumed_body() throws Exception {
		Connector connector = new NettyConnector();
		connector.port(8080).dispatcher(mockDispatcher()).start();
		String returnString = Request.Get("http://127.0.0.1:8080/").connectTimeout(1000).socketTimeout(1000).execute()
				.returnContent().asString();
		assertThat(returnString).isEqualTo(HELLO_WORLD);
		connector.stop();
	}

	private Dispatcher mockDispatcher() {
		return new Dispatcher() {
			@Override
			public void dispatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
				response.getOutputStream().print(HELLO_WORLD);
			}
		};
	}

	@Test
	public void should_dispatcher_get_assumed_param() throws Exception {
		Connector connector = new NettyConnector();
		connector.port(8080).dispatcher(new Dispatcher() {
			@Override
			public void dispatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
				assertThat(request.getHeader(TEST_HEADER)).isEqualTo(HELLO_WORLD);
				assertThat(request.getRequestURI()).isEqualTo("/"+HELLO_WORLD);
                response.getOutputStream().print(HELLO_WORLD);
			}
		}).start();
		Request.Get("http://127.0.0.1:8080/" + HELLO_WORLD).connectTimeout(1000)
				.socketTimeout(1000).addHeader(TEST_HEADER, HELLO_WORLD).execute().returnContent().asString();
		connector.stop();
	}

}
