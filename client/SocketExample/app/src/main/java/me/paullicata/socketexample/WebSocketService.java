package me.paullicata.socketexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Paul on 5/1/18.
 */

public class WebSocketService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;

    }


}
