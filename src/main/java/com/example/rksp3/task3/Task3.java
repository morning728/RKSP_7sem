package com.example.rksp3.task3;

import io.reactivex.rxjava3.core.Observable;

import java.util.Random;

public class Task3 {

    public static UserFriend[] generateUserFriends(int count) {
        Random random = new Random();
        UserFriend[] userFriends = new UserFriend[count];

        for (int i = 0; i < count; i++) {
            int userId = random.nextInt(100);  // случайный userId от 0 до 99
            int friendId = random.nextInt(100);  // случайный friendId от 0 до 99
            userFriends[i] = new UserFriend(userId, friendId);
        }

        return userFriends;
    }

    // Функция, возвращающая поток объектов UserFriend
    public static Observable<UserFriend> getFriends(UserFriend[] userFriends, int userId) {
        return Observable.fromArray(userFriends).filter(userFriend -> userFriend.getFriendId() == userId);  // Формирование потока из массива
    }

    public static void main(String[] args) {
        Random random = new Random();
        // Пример генерации массива
        UserFriend[] userFriends = generateUserFriends(1000);  // Генерим 1000 друзей

        Observable<UserFriend> randomUserId = Observable.range(1, 1000)
                .map(i -> random.nextInt(100))//поток случайных userId
                .flatMap(integer -> getFriends(userFriends, integer));//поток друзей этих айдишников
    }
}
