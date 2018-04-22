package com.example.austinsehnert.smartart;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by Paul on 4/19/18.
 */

public class SocketService extends IntentService {

    public SocketService() {
        super("SocketService");
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        WebSocket w = new WebSocket();
        w.connectWebSocket();
    }
}
