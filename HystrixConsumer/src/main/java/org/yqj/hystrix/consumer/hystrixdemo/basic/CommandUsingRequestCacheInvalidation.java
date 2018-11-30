package org.yqj.hystrix.consumer.hystrixdemo.basic;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

/**
 * Description: prefix 支持 Cache Invalidation
 *
 * @author yaoqijun
 * @date 2018-11-30
 * Email: yaoqijunmail@foxmail.com
 */
public class CommandUsingRequestCacheInvalidation {

    public static volatile String prefixStoredOnRemoteDataStore = "ValueBeforeSet_";

    public static class GetterCommand extends HystrixCommand<String> {

        private final int id;

        private static final HystrixCommandKey GETTER_KEY = HystrixCommandKey.Factory.asKey("GetterCommand");

        public GetterCommand(int id) {
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetterCommand")).andCommandKey(GETTER_KEY));
            this.id = id;
        }

        @Override
        protected String run() throws Exception {
            return prefixStoredOnRemoteDataStore + id;
        }

        @Override
        protected String getCacheKey() {
            return String.valueOf(id);
        }

        public static void flushCache(int id) {
            HystrixRequestCache.getInstance(GETTER_KEY,
                    HystrixConcurrencyStrategyDefault.getInstance()).clear(String.valueOf(id));
        }
    }

    public static class SetterCommand extends HystrixCommand<Void> {

        private final int id;

        private final String prefix;

        public SetterCommand(int id, String prefix) {
            super(HystrixCommandGroupKey.Factory.asKey("GetSetGet"));
            this.id = id;
            this.prefix = prefix;
        }

        @Override
        protected Void run() {
            // persist the value against the datastore
            prefixStoredOnRemoteDataStore = prefix;
            // flush the cache
            GetterCommand.flushCache(id);
            // no return value
            return null;
        }
    }

    public static void main(String[] args) {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
//            System.out.println(new GetterCommand(1).execute());
//
            GetterCommand commandAgainstCache = new GetterCommand(1);
            System.out.println(commandAgainstCache.execute());
            System.out.println(commandAgainstCache.isResponseFromCache());
            new SetterCommand(1, "ValueAfterSet_").execute();
            GetterCommand commandAfterSet = new GetterCommand(1);
            System.out.println(commandAfterSet.isResponseFromCache());
            System.out.println(commandAfterSet.execute());
        } finally {
            context.shutdown();
        }
    }

}
