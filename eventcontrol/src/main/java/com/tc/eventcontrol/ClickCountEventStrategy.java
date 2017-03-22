package com.tc.eventcontrol;


/**
 * author：   tc
 * date：     2017/3/21 & 下午2:58
 * version    1.0
 * description 通过点击次数控制方法的执行.当前点击次数超过设定的最大值时，满足触发条件。
 * modify by
 */
public class ClickCountEventStrategy implements IEventStrategy {


    @Override
    public boolean canTriggeredEvent(EventEntity entity) {
        int count = entity.getCurrentDelayClickCount() + 1;
        boolean b = count >= entity.getDelayClickCount();
        if (b) {
            count = 0;//满足条件，重置次数
        }
        entity.setCurrentDelayClickCount(count);
        EventTriggeredController.getInstance().addEventStrategy(entity, this);
        return b;
    }
}
