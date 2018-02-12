package com.example.developer.testashmem;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;



public class ShmService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new SharedMemImp();
    }
}
