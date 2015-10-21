package com.android.scanner;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class BaseService extends Service implements BLEScanCallback {
    private static final String TAG = BaseService.class.getSimpleName();

    private BLEScanner bleScanner;

    @Override
    public void onCreate() {
        super.onCreate();

        bleScanner = BLEScanner.createScanner(getApplicationContext(), this);
        List<ScanBLEFilter> scanBLEResults = new ArrayList<>();
        ScanBLEFilter scanBLEFilter = new ScanBLEFilter.Builder()
//                .setServiceUuid(BLEFilter.createServiceDataUUID(BLEFilter.SERVICE_UUID_EDDYSTONE))
                .build();
        scanBLEResults.add(scanBLEFilter);
        bleScanner.setScanBLEFilters(scanBLEResults);
        bleScanner.start();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        bleScanner.stop();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLeScan(ScanBLEResult scanBLEResult) {
        Log.v(TAG, "scanBLEResult:" + scanBLEResult.getDevice().getAddress());
    }

    @Override
    public void onScanCycleFinish() {
        Log.v(TAG,"onScanCycleFinish");
    }
}
