package org.yqj.hystrix.consumer.hystrixdemo;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import rx.Observable;

import java.util.concurrent.Future;

/**
 * Description: 测试demo hystrix
 *
 * @author yaoqijun
 * @date 2018-11-29
 * Email: yaoqijunmail@foxmail.com
 */
public class CommandHelloWorld extends HystrixCommand<String> {

    private String name;

    public CommandHelloWorld(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        return "Hello " + name + "!";
    }

    public static void main(String[] args) throws Exception {
        String s = new CommandHelloWorld("Bob").execute();
        System.out.println(s);
        Future<String> sf = new CommandHelloWorld("Bob").queue();
        System.out.println(sf.get());
        Observable<String> so = new CommandHelloWorld("Bob").observe();
        so.subscribe(System.out::println);
    }
}
