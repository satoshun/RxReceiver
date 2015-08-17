package jp.satoshun.rxreceiver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import rx.Observable;
import rx.Subscriber;

class LocalBroadcastReceiverOnSubscribe implements Observable.OnSubscribe<Intent> {

    private final Context context;
    private final IntentFilter filter;

    public LocalBroadcastReceiverOnSubscribe(Context context, IntentFilter filter) {
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

        LocalBroadcastManager.getInstance(context)
                .registerReceiver(receiver, filter);
        subscriber.add(new MainHandlerSubscription() {
            @Override
            public void run() {
                LocalBroadcastManager.getInstance(context)
                        .unregisterReceiver(receiver);
            }
        });
    }
}
