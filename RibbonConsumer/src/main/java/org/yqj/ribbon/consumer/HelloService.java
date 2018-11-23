package org.yqj.ribbon.consumer;

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

    public String hiService(){
        return restTemplate.getForObject("http://SERVICE-HI-V1/", String.class);
    }
}
