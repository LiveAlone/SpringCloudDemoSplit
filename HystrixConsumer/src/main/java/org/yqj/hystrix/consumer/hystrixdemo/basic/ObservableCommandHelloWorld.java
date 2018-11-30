package org.yqj.hystrix.consumer.hystrixdemo.basic;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Subscriber;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2018-11-30
 * Email: yaoqijunmail@foxmail.com
 */
public class ObservableCommandHelloWorld extends HystrixObservableCommand<String> {

    private String name;

    protected ObservableCommandHelloWorld(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected Observable<String> construct() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()){
                        subscriber.onNext("hello ");
                        subscriber.onNext(name + " !");
                        subscriber.onCompleted();
                    }
                }catch (Exception e){
                    subscriber.onError(e);
                }
            }
        });
    }

    public static void main(String[] args) {
        ObservableCommandHelloWorld helloWorld = new ObservableCommandHelloWorld("World");
        helloWorld.observe().subscribe(System.out::println);
    }
}
