package com.github.satoshun.rxreceiver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import rx.Observable;
import rx.Subscriber;

class BroadcastReceiverOnSubscribe implements Observable.OnSubscribe<Intent> {

    private final Context context;
    private final IntentFilter filter;

    public BroadcastReceiverOnSubscribe(Context context, IntentFilter filter) {
        this.context = context;
        this.filter = filter;
    }

    @Override
    public void call(final Subscriber<? super Intent> subscriber) {
        final BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                subscriber.onNext(intent);
            }
        };

        context.registerReceiver(receiver, filter);
        subscriber.add(new MainHandlerSubscription() {
            @Override
            public void run() {
                context.unregisterReceiver(receiver);
            }
        });
    }
}
