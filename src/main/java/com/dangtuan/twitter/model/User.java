package com.dangtuan.twitter.model;

import java.util.List;
import java.util.Set;

public class User {

  Set<Integer> friends;
  List<Tweet> posts;
  private int id;

  public User(int userId) {
    this.id = userId;
  }

  public Set<Integer> getFriends() {
    return friends;
  }

  public void setFriends(Set<Integer> friends) {
    this.friends = friends;
  }

  public List<Tweet> getPosts() {
    return posts;
  }

  public void setPosts(List<Tweet> posts) {
    this.posts = posts;
  }

  public int getId() {
    return id;
  }
}
