package www.melody.chat.http;

import android.accounts.NetworkErrorException;

import www.melody.chat.utils.NetworkUtils;

import rx.Subscriber;

/**
 * 订阅基类
 * Created by ZhengSheng on 2017/3/20.
 */

public abstract class BaseSubscriber<T> extends Subscriber<T> {

    private T t;

    public abstract void onSuccess(T t);
    public abstract void onFailure(Throwable e);

    @Override
    public void onCompleted() {
        System.out.println("onCompleted");
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        System.out.println("onError");
        onFailure(e);
    }

    @Override
    public void onNext(T t) {
        System.out.println("onNext");
        this.t = t;
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("onStart");
        // 判断是否有网络
        if (NetworkUtils.isConnected()) {
            System.out.println("onStart2");
        } else {
            // 没有网络则取消订阅
            System.out.println("unsubscribe");
            unsubscribe();
            onFailure(new NetworkErrorException());
        }
    }
}
