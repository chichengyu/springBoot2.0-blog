package com.blog.interceptor;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web 配置
 * 方式一：直接实现 WebMvcConfigurer  （推荐）
 * 方案2 直接继承 WebMvcConfigurationSupport
 *     继承WebMvcConfigurationSupport类，在扩展的类中重写父类的方法即可，
 *     但这种方式会有问题的，这种方式会屏蔽Spring Boot的@EnableAutoConfiguration中的设置。
 *     这时候启动项目时会发现映射根本没有加载成功，
 *     读取不到静态的资源也就是说application.properties中添加配置的映射配置没有起作用，
 *     然后我们会想到重写来进行重新映射
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    // 解决跨域问题
    // public void addCorsMappings(CorsRegistry registry) ;
    // 添加拦截器
    // public void addInterceptors(InterceptorRegistry registry);
    // 这里配置视图解析器
    // public void configureViewResolvers(ViewResolverRegistry registry);
    // 配置内容裁决的一些选项
    // public void configureContentNegotiation(ContentNegotiationConfigurer configurer);
    // 视图跳转控制器
    // public void addViewControllers(ViewControllerRegistry registry);
    // 静态资源处理
    // public void addResourceHandlers(ResourceHandlerRegistry registry);
    // 默认静态资源处理器
    // public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer);

    /**
     * 配置拦截路由
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login","/admin/logout","/css/**","/js/**","/images/**","/lib/**");
    }
}
