package org.yqj.hystrix.consumer.hystrixdemo.basic;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

/**
 * Description: 添加缓存支持
 *
 * @author yaoqijun
 * @date 2018-11-30
 * Email: yaoqijunmail@foxmail.com
 */
public class CommandUsingRequestCache extends HystrixCommand<Boolean> {

    private final int value;

    public CommandUsingRequestCache(int value) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.value = value;
    }

    @Override
    protected Boolean run() throws Exception {
        return value % 2 ==0;
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(value);
    }

    public static void main(String[] args) {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();

        CommandUsingRequestCache cacheA = new CommandUsingRequestCache(2);
        CommandUsingRequestCache cacheB = new CommandUsingRequestCache(2);
//        CommandUsingRequestCache cacheB = new CommandUsingRequestCache(4);    // 对于相同的Key 支持缓存的方式
        System.out.println(cacheA.execute());
        System.out.println(cacheA.isResponseFromCache());
        System.out.println(cacheB.execute());
        System.out.println(cacheB.isResponseFromCache());

    }
}
