package com.tc.eventcontrol;

/**
 * author：   tc
 * date：     2017/3/21 下午3:41
 * version    1.0
 * description 构造事件实体信息
 * modify by
 */
public class EventEntityBuilder {
    private EventEntity mEventEntity = new EventEntity();


    public EventEntityBuilder setDelayTime(long time) {
        mEventEntity.setDelayTime(time);
        return this;
    }

    public EventEntityBuilder setEventTag(String tag) {
        mEventEntity.setEventTag(tag);
        return this;
    }

    public EventEntityBuilder setDelayClickCount(int count) {
        mEventEntity.setDelayClickCount(count);
        return this;
    }

    public EventEntity create() {
        return mEventEntity;
    }
}
