package org.yqj.hystrix.consumer.hystrixdemo.basic;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2018-11-30
 * Email: yaoqijunmail@foxmail.com
 */
public class CommandHelloWorldFailure extends HystrixCommand<String> {

    private String name;

    public CommandHelloWorldFailure(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        throw new RuntimeException("this command run failure");
    }

    @Override
    protected String getFallback() {
        return String.format("Hello failure %s !", this.name);
    }

    public static void main(String[] args) {
        System.out.println(new CommandHelloWorldFailure("yaoqijun").execute());
    }
}
