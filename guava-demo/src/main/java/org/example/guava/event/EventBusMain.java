package org.example.guava.event;

import com.google.common.eventbus.EventBus;

/**
 * @author qinfengsa
 * @date 2022/02/09 10:30
 */
public class EventBusMain {

    private static final EventBus eventBus = new EventBus("main");

    public static void main(String[] args) {
        EventBus eventBus = createEventBus();
        // 注册 listener
        eventBus.register(new UserCreatedEventListener());
        // 发布消息
        eventBus.post(new UserCreatedEvent(1, "qin"));
    }

    static EventBus createEventBus() {
        return eventBus;
    }
}
