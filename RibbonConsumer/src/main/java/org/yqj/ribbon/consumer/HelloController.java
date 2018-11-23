package org.yqj.ribbon.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2018/11/23
 * Email: qijunyao@xiaohongshu.com
 */
@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @Autowired
    private FeignHelloService feignHelloService;

    @RequestMapping(value = "/")
    public String hi(){
        return helloService.hiService();
    }

    @RequestMapping(value = "/feign")
    public String hiWithFeign(){
        return feignHelloService.sayHi();
    }
}
