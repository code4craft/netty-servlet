package us.codecraft.express.connector;

import org.apache.http.client.fluent.Request;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import us.codecraft.netty_servlet.connector.Connector;
import us.codecraft.netty_servlet.connector.netty.NettyConnector;
import us.codecraft.netty_servlet.container.Dispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yihua.huang@dianping.com
 */
public class NettyConnectorTest {

    public static final String HELLO_WORLD = "hello world!";

    @Test
    public void should_mock_dispatcher_return_assumed_body() throws Exception {
        Connector connector = new NettyConnector();
        connector.port(8080).dispatcher(mockDispatcher()).start();
        String returnString = Request.Get("http://127.0.0.1:8080/")
                .connectTimeout(1000)
                .socketTimeout(1000)
                .execute().returnContent().asString();
        Assertions.assertThat(returnString).isEqualTo(HELLO_WORLD);
        connector.stop();
    }

    private Dispatcher mockDispatcher(){
        return new Dispatcher() {
            @Override
            public void dispatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
                response.getOutputStream().print(HELLO_WORLD);
            }
        };
    }
}
