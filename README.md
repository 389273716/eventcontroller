# eventcontroller
# 事件控制器


>介绍
 * 控制事件被触发。比如间隔一段时间、操作中无法触发、多次点击才触发,特定时间内多次点击触发等
 * 作用：保护某些方法不被反复调用，或者再特定策略下才被执行，避免无效的重复触发。
 * 使用方式：设置EventEntity的对应值，使用默认的Strategy策略，或者自行实现IEventStrategy接口实现自定义策略。
 调用addEventStrategy()方法添加策略，调用doStrategy()执行策略判断，remove()方法移除，或者destroy清除所有策略。
 *使用方式参照app下的demo。

compile 'com.tc.eventcontrol:eventcontrol:0.1.0'
