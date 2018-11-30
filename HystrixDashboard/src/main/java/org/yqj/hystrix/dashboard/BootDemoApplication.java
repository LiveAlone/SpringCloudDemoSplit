package org.yqj.hystrix.dashboard;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2018/8/15
 * Email: yaoqijunmail@foxmail.com
 */
@SpringBootApplication
@EnableEurekaClient
@EnableHystrixDashboard
public class BootDemoApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(BootDemoApplication.class)
                .run(args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
