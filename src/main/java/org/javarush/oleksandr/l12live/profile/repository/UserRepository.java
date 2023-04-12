package org.javarush.oleksandr.l12live.profile.repository;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private final Map<String, User> users = new HashMap<>(){{
        User admin = new User("Admin", "Admin", "Admin@jr.com", "root");
        put(admin.getUserName(), admin);
    }};

    private static UserRepository instance;

    private UserRepository() {
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }

        return instance;
    }

    public void add(User user) {
        users.put(user.getUserName(), user);
    }

    public User get(String userName) {
        return users.get(userName);
    }

    public boolean contains(String userName){
        return users.containsKey(userName);
    }
}
