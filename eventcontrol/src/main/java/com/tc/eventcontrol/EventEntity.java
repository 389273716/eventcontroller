package com.tc.eventcontroller;

/**
 * author：   tc
 * date：     2017/3/21 & 下午2:49
 * version    1.0
 * description 事件触发条件实体
 * modify by
 */
public class EventEntity {
    /**
     * 延迟执行时间
     */
    private long delayTime;
    /**
     * 记录的上次触发时间
     */
    private long recordLastTime;
    /**
     * 操作中
     */
    private boolean isOperating;
    /**
     * 当前策略逻辑所对应的唯一标识tag，用于区分每次的判断
     */
    private String eventTag;
    /**
     * 默认间隔执行次数，用于双击或者多次点击触发的逻辑
     */
    private int delayClickCount;
    /**
     * 当前已间隔执行次数，用于双击或者多次点击触发的逻辑
     */
    private int currentDelayClickCount;

    public long getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(long delayTime) {
        this.delayTime = delayTime;
    }

    public long getRecordLastTime() {
        return recordLastTime;
    }

    public void setRecordLastTime(long recordLastTime) {
        this.recordLastTime = recordLastTime;
    }

    public boolean isOperating() {
        return isOperating;
    }

    public void setOperating(boolean operating) {
        isOperating = operating;
    }

    public String getEventTag() {
        return eventTag == null ? "" : eventTag;
    }

    public void setEventTag(String eventTag) {
        this.eventTag = eventTag;
    }

    public int getDelayClickCount() {
        return delayClickCount;
    }

    public void setDelayClickCount(int delayClickCount) {
        this.delayClickCount = delayClickCount;
    }

    public int getCurrentDelayClickCount() {
        return currentDelayClickCount;
    }

    public void setCurrentDelayClickCount(int currentDelayClickCount) {
        this.currentDelayClickCount = currentDelayClickCount;
    }



}
