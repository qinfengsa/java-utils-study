package org.example.guava.event;

/**
 * 用户创建事件
 *
 * @author qinfengsa
 * @date 2022/02/09 11:28
 */
public class UserCreatedEvent {

    private final Integer userId;

    private final String userName;

    public UserCreatedEvent(Integer userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }
}
