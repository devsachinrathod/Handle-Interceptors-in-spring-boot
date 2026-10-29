package in.strikes.InterceptorsClass.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoggingInterceptors implements HandlerInterceptor {

    // 1. Before controller execution
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {

        System.out.println("Incoming Request --------");

        System.out.println("HTTP Method: " + request.getMethod());
        System.out.println("Request URI: " + request.getRequestURI());
        System.out.println("Request Parameters: " + request.getQueryString());
        System.out.println("Client IP: " + request.getRemoteAddr());
        System.out.println("Token Header: " + request.getHeader("token"));

        if (handler instanceof HandlerMethod handlerMethod) {
            System.out.println("Controller: " + handlerMethod.getBeanType().getName());
            System.out.println("Controller Method: " + handlerMethod.getMethod().getName());
        }

        // Remaining code is not visible in the image.
        return true;
    }


    // 2. After controller execution
    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView) throws Exception {

        System.out.println("PostHandle method called");
    }

    // 3. After request processing is completed
    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex) throws Exception {

        System.out.println("AfterCompletion method called");
    }
}