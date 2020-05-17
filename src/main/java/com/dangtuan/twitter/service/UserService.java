package com.dangtuan.twitter.service;

import com.dangtuan.twitter.model.User;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class UserService {

  private static UserService userService;
  private HashMap<Integer, User> users = new LinkedHashMap<>();

  /**
   * Method create and get single instance of UserService
   *
   * @return instance of UserService
   */
  public static synchronized UserService getUserServiceInstance() {
    if (userService == null) {
      userService = new UserService();
    }
    return userService;
  }

  public User getUserById(final int userId) {
    if (users.containsKey(userId)) {
      return users.get(userId);
    } else {
      return null;
    }
  }

  public User addUser(final int userId) {
    final User user = new User(userId);
    users.put(userId, user);
    return user;
  }

}
