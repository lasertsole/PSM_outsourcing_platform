package com.psm.config;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @BelongsProject: psm-backend
 * @BelongsPackage: com.psm.config
 * @Author: ChenLan
 * @Date: 2023/10/24 19:12
 * @Description:解决springboot静态资源上传无法立即访问问题
 */
@Configuration
public class BaseInterceptor extends WebMvcConfigurationSupport {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());

        String absoluteImagePath = applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath()+
                "\\src\\main\\resources\\static\\images\\";
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:"+absoluteImagePath);

        String absoluteVideoPath = applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath()+
                "\\src\\main\\resources\\static\\Video\\";
        registry.addResourceHandler("/Video/**")
                .addResourceLocations("file:"+absoluteVideoPath);
    }
}
