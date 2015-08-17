package jp.satoshun.rxreceiver;


import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.CheckResult;

import rx.Observable;

public class RxReceiver {

    @CheckResult
    public static Observable<Intent> registerReceiver(Context context, String action) {
        return registerReceiver(context, new IntentFilter(action));
    }

    @CheckResult
    public static Observable<Intent> registerReceiver(Context context, IntentFilter filter) {
        return Observable.create(new ReceiverEventOnSubscribe(context.getApplicationContext(), filter));
    }
}
