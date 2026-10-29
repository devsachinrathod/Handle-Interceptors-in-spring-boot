package in.strikes.InterceptorsClass.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthenticationInterceptors implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationInterceptors.class);

    private static final String LOCAL_TOKEN = "1234"; // local testing only

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        log.info("Request: {} {}", request.getMethod(), request.getRequestURI());

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.warn("Auth failed: missing or invalid Authorization header");
            return unauthorized(response, "Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7);

        if (!LOCAL_TOKEN.equals(token)) {
            log.warn("Auth failed: invalid token");
            return unauthorized(response, "Invalid token");
        }

        log.info("Auth success");
        return true;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex) throws Exception {

        System.out.println("AfterCompletion method called");
    }

    private boolean unauthorized(HttpServletResponse response, String message) throws Exception {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\":\"" + message + "\"}");
        return false;
    }
}