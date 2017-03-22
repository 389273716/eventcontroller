package com.tc.eventcontroller;


import android.util.Log;

/**
 * author：   tc
 * date：     2017/3/22 & 上午9:26
 * version    1.0
 * description 策略：特定时间内双击才触发事件。delaytime内curDelayClickCount>=delayClickCount
 * modify by
 */
public class DoubleClickStrategy implements IEventStrategy {
    private static final String TAG = DoubleClickStrategy.class.getSimpleName();

    @Override
    public boolean canTriggeredEvent(EventEntity entity) {
        int count = entity.getCurrentDelayClickCount() + 1;
        boolean isDouble = count >= entity.getDelayClickCount();
        boolean isInTime = System.currentTimeMillis() - entity.getRecordLastTime() <= entity
                .getDelayTime();
        boolean b = isDouble && isInTime;
        Log.i(TAG, "canTriggeredEvent: cur time: " + System.currentTimeMillis() + " " +
                "RecordLastTime:" +
                entity.getRecordLastTime());
        Log.i(TAG, "canTriggeredEvent: count: " + count + " isInTime:" + isInTime);
        if (!isInTime) {
            //超出时间：更新记录的时间，重置点击次数，从本次开始计数
            entity.setCurrentDelayClickCount(1);
            entity.setRecordLastTime(System.currentTimeMillis());
            EventTriggeredController.getInstance().addEventStrategy(entity, this);
        } else {
            //在特定时间内，记录下每次的点击次数
            entity.setCurrentDelayClickCount(count);//记录当前延迟执行的次数
            EventTriggeredController.getInstance().addEventStrategy(entity, this);
        }

        if (b) {
            //满足条件
            entity.setCurrentDelayClickCount(0);
            entity.setRecordLastTime(System.currentTimeMillis());
            EventTriggeredController.getInstance().addEventStrategy(entity, this);
        }
        return b;
    }
}
