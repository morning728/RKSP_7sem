package com.example.rksp3.task3;

public class UserFriend {
    private int userId;
    private int friendId;

    public UserFriend(int userId, int friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }

    public int getUserId() {
        return userId;
    }

    public int getFriendId() {
        return friendId;
    }

    @Override
    public String toString() {
        return "UserFriend{" +
                "userId=" + userId +
                ", friendId=" + friendId +
                '}';
    }
}

