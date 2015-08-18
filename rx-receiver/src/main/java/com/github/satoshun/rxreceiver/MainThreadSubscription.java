package com.github.satoshun.rxreceiver;

import android.os.Handler;
import android.os.Looper;

import rx.Subscription;


abstract class MainHandlerSubscription implements Subscription, Runnable {
    static final Handler MAIN_HANDLER = new Handler(Looper.getMainLooper());

    private volatile boolean unsubscribed = false;

    @Override
    public void unsubscribe() {
        unsubscribed = true;
        if (Looper.getMainLooper() == Looper.myLooper()) {
            run();
        } else {
            MAIN_HANDLER.post(this);
        }
    }

    @Override
    public boolean isUnsubscribed() {
        return unsubscribed;
    }
}
