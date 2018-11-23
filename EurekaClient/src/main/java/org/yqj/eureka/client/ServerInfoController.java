package org.yqj.eureka.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2018/11/23
 * Email: qijunyao@xiaohongshu.com
 */
@Controller
@Slf4j
public class ServerInfoController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/list_servers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String listServersInfo(){
        List<String> services = discoveryClient.getServices();
        log.info("eureka services is content {}", services);
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("eureka-server");
        for (ServiceInstance serviceInstance : serviceInstances) {
            log.info("serviceInstance {} is info, string info :{}", serviceInstance.getUri(), serviceInstance.toString());
        }
        return "SUCCESS";
    }
}
