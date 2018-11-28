package org.yqj.hystrix.consumer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2018/11/23
 * Email: qijunyao@xiaohongshu.com
 */
@Service
public class HelloService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallback")
    public String hiService(){
        return restTemplate.getForObject("http://SERVICE-HI-V1/", String.class);
    }

    @HystrixCommand(fallbackMethod = "fallback")
    public String delay(){
        return restTemplate.getForObject("http://SERVICE-HI-V1/delay", String.class);
    }

    @HystrixCommand(fallbackMethod = "fallback")
    public String exception(){
        return restTemplate.getForObject("http://SERVICE-HI-V1/exception", String.class);
    }

    public String fallback() {
        return "fallback";
    }
}
