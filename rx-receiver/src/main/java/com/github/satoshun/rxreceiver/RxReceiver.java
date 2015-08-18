package com.github.satoshun.rxreceiver;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.CheckResult;

import rx.Observable;

public class RxReceiver {
    @CheckResult
    public static Observable<Intent> registerBroadcastReceiver(Context context, String action) {
        return registerBroadcastReceiver(context, new IntentFilter(action));
    }

    @CheckResult
    public static Observable<Intent> registerBroadcastReceiver(Context context, IntentFilter filter) {
        return Observable.create(
                new BroadcastReceiverOnSubscribe(context.getApplicationContext(), filter));
    }

    @CheckResult
    public static Observable<Intent> registerLocalBroadcastReceiver(Context context, String action) {
        return registerLocalBroadcastReceiver(context, new IntentFilter(action));
    }

    @CheckResult
    public static Observable<Intent> registerLocalBroadcastReceiver(Context context, IntentFilter filter) {
        return Observable.create(
                new LocalBroadcastReceiverOnSubscribe(context.getApplicationContext(), filter));
    }
}
