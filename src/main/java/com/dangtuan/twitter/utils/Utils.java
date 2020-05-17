package com.dangtuan.twitter.utils;

import com.dangtuan.twitter.service.UserService;

public class Utils {

  public void initData() {
    UserService.getUserServiceInstance().addUser(1);
    UserService.getUserServiceInstance().addUser(2);
  }
}
