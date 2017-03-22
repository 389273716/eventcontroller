package com.tc.eventcontroller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.tc.eventcontrol.EventTriggeredController;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ToastUtil.init(getApplicationContext());
    }


    public void onDelayTimeClick(View view) {
        if (EventTriggeredController.getInstance().doDelayTimeStrategy("delaytimetag", 2000)) {
            Log.i(TAG, "onDelayTimeClick: 满足条件");
            ToastUtil.show("满足条件！", 200);
        } else {
            ToastUtil.show("不满足条件！", 200);
            Log.i(TAG, "onDelayTimeClick: 不满足条件");

        }
    }

    public void onDelayCountClick(View view) {
        if (EventTriggeredController.getInstance().doClickCountStrategy("clickcount", 2)) {
            Log.i(TAG, "onDelayCountClick: 满足条件");

            ToastUtil.show("满足条件！", 200);
        } else {
            ToastUtil.show("不满足条件！", 200);
            Log.i(TAG, "onDelayCountClick: 不满足条件");

        }
    }

    public void onDelayCountWithDefaultTimeClick(View view) {
        if (EventTriggeredController.getInstance().doDoubleClickStrategy("doubleclick", 2000, 2)) {
            Log.i(TAG, "onDelayCountWithDefaultTimeClick: 满足条件");

            ToastUtil.show("满足条件！", 200);
        } else {
            ToastUtil.show("不满足条件！", 200);
            Log.i(TAG, "onDelayCountWithDefaultTimeClick: 不满足条件");

        }
    }

    public void onOperatingClick(View view) {
        if (EventTriggeredController.getInstance().doOperatingStrategy("isoperating")) {
            Log.i(TAG, "onOperatingClick: 满足条件");

            ToastUtil.show("满足条件！", 200);
        } else {
            ToastUtil.show("不满足条件！", 200);
            Log.i(TAG, "onOperatingClick: 不满足条件");

        }
    }

    public void onResetOperatingStatus(View view) {
        EventTriggeredController.getInstance().resetOperatingStatus("isoperating");
        Log.i(TAG, "onResetOperatingStatus: 重置状态");

    }
}
