package com.tc.eventcontrol;


/**
 * author：   tc
 * date：     2017/3/21  下午2:45
 * version    1.0
 * description 判断是否可以出发事件执行的策略接口
 * modify by
 */
public interface IEventStrategy {
    /**
     * 是否可以继续触发执行方法
     * @param entity 当前触发器实体
     * @return 返回是否满足条件触发事件
     */
    boolean canTriggeredEvent(EventEntity entity);
}
