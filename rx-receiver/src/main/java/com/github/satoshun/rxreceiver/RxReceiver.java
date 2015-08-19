package com.github.satoshun.rxreceiver;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.CheckResult;

import rx.Observable;

/** RxReceiver is factory methods for creating {@link Observable}. */
public class RxReceiver {

    /**
     * Create a {@link Observable} that emits on broadcast {@link Intent}.
     * specify action for {@link IntentFilter}.
     */
    @CheckResult
    public static Observable<Intent> registerBroadcastReceiver(Context context, String action) {
        return registerBroadcastReceiver(context, new IntentFilter(action));
    }

    /** Create a {@link Observable} that emits on broadcast {@link Intent}. */
    @CheckResult
    public static Observable<Intent> registerBroadcastReceiver(Context context, IntentFilter filter) {
        return Observable.create(
                new BroadcastReceiverOnSubscribe(context.getApplicationContext(), filter));
    }

    /**
     * Create a {@link Observable} that emits on local broadcast {@link Intent}.
     * specify action for {@link IntentFilter}.
     */
    @CheckResult
    public static Observable<Intent> registerLocalBroadcastReceiver(Context context, String action) {
        return registerLocalBroadcastReceiver(context, new IntentFilter(action));
    }

    /** Create a {@link Observable} that emits on local broadcast {@link Intent}. */
    @CheckResult
    public static Observable<Intent> registerLocalBroadcastReceiver(Context context, IntentFilter filter) {
        return Observable.create(
                new LocalBroadcastReceiverOnSubscribe(context.getApplicationContext(), filter));
    }
}
