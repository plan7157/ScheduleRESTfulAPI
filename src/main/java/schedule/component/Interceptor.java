package schedule.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interceptor Class
 * this class use to be logging for bug investigation.
 */
@Component
@Slf4j
public class Interceptor extends HandlerInterceptorAdapter {

    /**
     * this class is will be work before controller
     *
     * @param httpServletRequest  this is Request from user
     * @param httpServletResponse this is response to user
     * @param o                   this is http Body
     * @return true always
     * @throws Exception use for error catching
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        log.info("Request : " + httpServletRequest.getMethod() + " " + httpServletRequest.getRequestURI());
        return true;
    }
}
