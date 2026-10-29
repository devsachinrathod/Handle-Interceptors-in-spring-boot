package in.strikes.InterceptorsClass.config;

import in.strikes.InterceptorsClass.interceptors.AuthenticationInterceptors;
import in.strikes.InterceptorsClass.interceptors.LoggingInterceptors;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoggingInterceptors loggingInterceptors;
    private final AuthenticationInterceptors authenticationInterceptors;

    public WebConfig(LoggingInterceptors loggingInterceptors,
                     AuthenticationInterceptors authenticationInterceptors) {
        this.loggingInterceptors = loggingInterceptors;
        this.authenticationInterceptors = authenticationInterceptors;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 1st: log every request
        registry.addInterceptor(loggingInterceptors)
                .addPathPatterns("/**");

        // 2nd: authenticate only API routes, skip public ones
        registry.addInterceptor(authenticationInterceptors)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/auth/**");
    }
}