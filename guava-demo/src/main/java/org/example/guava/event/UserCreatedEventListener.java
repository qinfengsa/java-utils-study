package org.example.guava.event;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用户创建 监听器
 *
 * @author qinfengsa
 * @date 2022/02/09 11:28
 */
public class UserCreatedEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCreatedEventListener.class);

    @Subscribe public void handle(UserCreatedEvent event) {
        LOGGER.debug("已创建用户；id={},name={}", event.getUserId(), event.getUserName());

    }
}
