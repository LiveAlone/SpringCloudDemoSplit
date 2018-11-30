package org.yqj.hystrix.consumer.hystrixdemo.basic;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import rx.Observable;
import rx.Observer;

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

        // 测试同步执行方式
//        String s = new CommandHelloWorld("World").execute();
//        System.out.println(s);

        // 异步执行方式, Feature 等待适用时候
//        Future<String> sf = new CommandHelloWorld("World").queue();
//        System.out.println(sf.get());

        // 监控执行方式
//        Observable<String> so = new CommandHelloWorld("World").observe();
//        System.out.println(so.toBlocking().single());
//        so.subscribe(new Observer<String>() {
//            @Override
//            public void onCompleted() {
//                System.out.println("on completed");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                System.out.println("on error");
//            }
//
//            @Override
//            public void onNext(String s) {
//                System.out.println(s);
//            }
//        });
//        so.subscribe(System.out::println);
    }
}
