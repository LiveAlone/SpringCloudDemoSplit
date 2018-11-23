package org.yqj.ribbon.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2018/11/28
 * Email: qijunyao@xiaohongshu.com
 */
@FeignClient(value = "SERVICE-HI-V1")
public interface FeignHelloService {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    String sayHi();

}
