package com.yancy.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 映射服务器视频的url
 *
 * 访问
 *
 * 上面的配置的作用是你访问 域名+/image/ 的路径时，sprint boot就会从服务器的目录/projects/video/下去找资源文件
 *
 * eg:
 * 服务器的目录有个wzxian.mp4的视频，绝对路径是/projects/video/wzxian.mp4
 * 浏览器通过 http://47.92.139.80:9090/video/wzxian.mp4
 * 就可以访问到这个视频了
 */
@Configuration
public class VideoConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/video/**").addResourceLocations("file:/projects/video/");

    }
}
