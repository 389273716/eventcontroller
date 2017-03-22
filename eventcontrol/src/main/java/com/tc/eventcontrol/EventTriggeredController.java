package com.tc.eventcontroller;


import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;

/**
 * author：   tc
 * date：     2017/3/21 & 下午2:32
 * version    1.0
 * description 方法事件控制器
 * 控制事件被触发。比如间隔一段时间、操作中无法触发、多次点击才触发,特定时间内多次点击触发等
 * 作用：保护某些方法不被反复调用，或者再特定策略下才被执行，避免无效的重复触发。
 * 使用方式：设置EventEntity的对应值，使用默认的Strategy策略，或者自行实现IEventStrategy接口实现自定义策略。调用addEventStrategy()
 * 方法添加策略，调用doStrategy()执行策略判断，remove()方法移除，或者destroy清除所有策略。
 * modify by
 */
public class EventTriggeredController {
    private static final String TAG = EventTriggeredController.class.getSimpleName();
    private HashMap<String, EventEntity> mEventEntities = new HashMap<>();
    private HashMap<String, IEventStrategy> mEventStrategyTags = new HashMap<>();

    private static class SingleInstance {
        private static final EventTriggeredController INSTANCE = new
                EventTriggeredController();
    }

    private EventTriggeredController() {
    }

    public static EventTriggeredController getInstance() {
        return SingleInstance.INSTANCE;
    }

    /**
     * 执行多次点击间隔判断
     * 执行策略判断，如果满足可触发条件，则返回true。
     *
     * @param tag             事件触发判断的唯一标识tag
     * @param delayClickCount 默认间隔次数
     * @return
     */
    public boolean doClickCountStrategy(String tag, int delayClickCount) {
        if (!mEventStrategyTags.containsKey(tag)) {
            EventEntity eventEntity = new EventEntityBuilder().setEventTag(tag).setDelayClickCount
                    (delayClickCount)
                    .create();
            addEventStrategy(eventEntity, new ClickCountEventStrategy());
        }
        return doStrategy(tag);
    }

    /**
     * 执行延迟时间策略
     * 执行策略判断，如果满足可触发条件，则返回true。
     *
     * @param tag       事件触发判断的唯一标识tag
     * @param delayTime 默认间隔时间
     * @return
     */
    public boolean doDelayTimeStrategy(String tag, long delayTime) {
        if (!mEventStrategyTags.containsKey(tag)) {
            EventEntity eventEntity = new EventEntityBuilder().setEventTag(tag).setDelayTime
                    (delayTime)
                    .create();
            addEventStrategy(eventEntity, new DelayTimeEventStrategy());
        }
        return doStrategy(tag);
    }

    /**
     * 执行操作中策略判断，如果操作中则返回false。重置状态调用resetOperatingStrategy方法
     * 执行策略判断，如果满足可触发条件，则返回true。
     *
     * @param tag 事件触发判断的唯一标识tag
     * @return
     */
    public boolean doOperatingStrategy(String tag) {
        if (!mEventStrategyTags.containsKey(tag)) {
            EventEntity eventEntity = new EventEntityBuilder().setEventTag(tag).create();
            addEventStrategy(eventEntity, new OperatingEventStrategy());
        }
        return doStrategy(tag);
    }

    /**
     * 执行特定时间内多次点击触发的策略，比如2秒内双击触发
     * 执行策略判断，如果满足可触发条件，则返回true。
     *
     * @param tag             事件触发判断的唯一标识tag
     * @param delayTime       默认间隔时间
     * @param delayClickCount 默认间隔次数
     * @return
     */
    public boolean doDoubleClickStrategy(String tag, long delayTime, int delayClickCount) {
        if (!mEventStrategyTags.containsKey(tag)) {
            EventEntity eventEntity = new EventEntityBuilder().setEventTag(tag).setDelayTime
                    (delayTime)
                    .setDelayClickCount(delayClickCount).create();
            addEventStrategy(eventEntity, new DoubleClickStrategy());
        }
        return doStrategy(tag);
    }

    /**
     * 增加一个策略控制事件触发
     *
     * @param eventEntity
     * @param iEventStrategy
     */
    public void addEventStrategy(EventEntity eventEntity, IEventStrategy iEventStrategy) {
        if (eventEntity == null || iEventStrategy == null || TextUtils.isEmpty(eventEntity
                .getEventTag
                        ())) {
            Log.e(TAG, "addEventStrategy: 非法参数传入！");
            return;
        }
//        if (mEventStrategyTags.containsKey(eventEntity.getEventTag())) {
//            return;
//        }
        mEventEntities.put(eventEntity.getEventTag(), eventEntity);
        mEventStrategyTags.put(eventEntity.getEventTag(), iEventStrategy);
    }

    /**
     * 执行策略判断，如果满足可触发条件，则返回true。
     *
     * @param tag 事件触发判断的唯一标识tag
     * @return
     */
    public boolean doStrategy(String tag) {
        IEventStrategy iEventStrategy = mEventStrategyTags.get(tag);
        EventEntity eventEntity = mEventEntities.get(tag);
        if (iEventStrategy == null) {
            Log.e(TAG, "doStrategy: 不存在该策略！");
            return false;
        }
        if (eventEntity == null) {
            Log.e(TAG, "doStrategy: 不存在策略对应的事件信息实体！");
            return false;
        }
        return iEventStrategy.canTriggeredEvent(eventEntity);
    }

    /**
     * 重置当前tag对应的逻辑参数isOperating的状态为false，即操作结束
     *
     * @param tag
     */
    public void resetOperatingStatus(String tag) {
        EventEntity eventEntity = mEventEntities.get(tag);
        if (eventEntity == null) return;
        eventEntity.setOperating(false);
//        mEventEntities.get(tag).setOperating();
    }

    /**
     * @param tag
     */
    public void removeStrategy(String tag) {
        if (mEventEntities == null || mEventEntities.size() <= 0) {
            Log.e(TAG, "removeStrategy: 移除一个策略失败，事件集合为空！");
            return;
        }
        if (mEventStrategyTags == null || mEventStrategyTags.size() <= 0) {
            Log.e(TAG, "removeStrategy: 移除一个策略失败，策略集合为空！");
            return;
        }
        Log.e(TAG, "removeStrategy: 移除一个策略！");
        mEventEntities.remove(tag);
        mEventStrategyTags.remove(tag);
    }

    public void destroy() {
        Log.e(TAG, "destroy: 清除所有策略！");
        mEventEntities.clear();
        mEventStrategyTags.clear();
    }

}
