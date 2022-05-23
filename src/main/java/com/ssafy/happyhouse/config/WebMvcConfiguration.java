package com.ssafy.happyhouse.config;

import com.ssafy.happyhouse.interceptor.JwtInterceptor;
import com.ssafy.happyhouse.listener.RootPathListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.ServletContextListener;

@EnableAspectJAutoProxy // <aop:aspectj-autoproxy></aop:aspectj-autoproxy> 설정
@MapperScan(basePackages = {"com.ssafy.happyhouse.model.dao", "com.ssafy.happyhouse.model.mapper"})
// <mybatis-spring:scan base-package="com.ssafy.guestbook.model.mapper"/> 설정
@EnableConfigurationProperties(KeyProperties.class)
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    private JwtInterceptor jwtInterceptor;
    @Autowired
    public void setJwtInterceptor(JwtInterceptor jwtInterceptor) {
        this.jwtInterceptor = jwtInterceptor;
    }


    // view-controller 등록
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/users/login_form").setViewName("login_form");
        registry.addViewController("/users/register_form").setViewName("register_form");
        registry.addViewController("/users/member_detail").setViewName("member_detail");
        registry.addViewController("/board/board_form").setViewName("board_form");
        registry.addViewController("/board/board_modify_form").setViewName("board_modify_form");

    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
////		registry.addInterceptor(jwtInterceptor).addPathPatterns("/**")// 기본 적용 경로
////				.excludePathPatterns(EXCLUDE_PATHS);// 적용 제외 경로
//        registry.addInterceptor(jwtInterceptor).addPathPatterns("/users/**", "/board/**", "/houses/**") // 기본 적용 경로
//                .excludePathPatterns(Arrays.asList("/users/info", "/users/login", "/users/check-id", "/users/sign-up"));// 적용 제외 경로
//    }

    // interceptor 등록
    // 방법1: 인터셉터 직접 생성 후 등록(bean등록X)
    // 방법2: bean등록된 인터셉터를 @Autowired로 주입받아 등록하기
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 방법1 사용
//        registry.addInterceptor(new LoginCheckInterceptor()).addPathPatterns("/board/**").addPathPatterns("/houses/**");
//    }

    // listener 등록
    @Bean
    public ServletListenerRegistrationBean<ServletContextListener> addListener() {
        ServletListenerRegistrationBean<ServletContextListener> bean
                = new ServletListenerRegistrationBean<ServletContextListener>();
        bean.setListener(new RootPathListener());
        return bean;
    }

    // resources mapping 설정
    // <!-- Handles HTTP GET requests for /resources/** by efficiently serving up static resources in the ${webappRoot}/resources directory -->
    // <resources mapping="/css/**" location="/resources/css/" />
    // <resources mapping="/img/**" location="/resources/img/" />
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/img/profile/**").addResourceLocations("/resources/img/profile/");
    }

    //  Interceptor를 이용해서 처리하므로 전역의 Cross Origin 처리를 해준다.
    @Override
    public void addCorsMappings(CorsRegistry registry) {
//		default 설정.
//		Allow all origins.
//		Allow "simple" methods GET, HEAD and POST.
//		Allow all headers.
//		Set max age to 1800 seconds (30 minutes).
        registry.addMapping("/**")
                .allowedOrigins("*")
//			.allowedOrigins("http://localhost:8080", "http://localhost:8081")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//			.allowedHeaders("*")
                .maxAge(6000);
    }
}