package com.tc.eventcontroller;


/**
 * author：   tc
 * date：     2017/3/21 & 下午4:15
 * version    1.0
 * description 延迟时间触发事件event
 * modify by
 */
public class DelayTimeEventStrategy implements IEventStrategy {

    @Override
    public boolean canTriggeredEvent(EventEntity entity) {
        boolean b = System.currentTimeMillis() - entity.getRecordLastTime() >= entity
                .getDelayTime();
        if (b) {
            entity.setRecordLastTime(System.currentTimeMillis());
            EventTriggeredController.getInstance().addEventStrategy(entity, this);
        }
        return b;
    }
}
