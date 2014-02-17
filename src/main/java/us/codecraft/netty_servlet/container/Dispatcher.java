package us.codecraft.netty_servlet.container;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yihua.huang@dianping.com
 */
public interface Dispatcher {

    public void dispatch(HttpServletRequest request,HttpServletResponse response) throws IOException;

}
