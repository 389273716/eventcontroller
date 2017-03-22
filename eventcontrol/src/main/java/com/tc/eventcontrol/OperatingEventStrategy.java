package com.tc.eventcontroller;

/**
 * author：   tc
 * date：     2017/3/21 & 下午4:20
 * version    1.0
 * description 是否事件event在执行中，如果在执行中则返回false，不触发。
 * modify by
 */
public class OperatingEventStrategy implements IEventStrategy {
    @Override
    public boolean canTriggeredEvent(EventEntity entity) {
        boolean b = entity.isOperating();
        if (!b) {
            b = true;
            entity.setOperating(true);
            EventTriggeredController.getInstance().addEventStrategy(entity, this);
        } else {
            b = false;
        }

        return b;
    }
}
