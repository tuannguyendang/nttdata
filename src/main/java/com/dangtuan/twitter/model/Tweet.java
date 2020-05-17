package com.dangtuan.twitter.model;

import com.dangtuan.twitter.constants.Constants;

public class Tweet {

  private int id;
  private int time;
  private String content;

  public Tweet(int id, int time) {
    this.id = id;
    this.time = time;
  }

  public int getId() {
    return id;
  }

  public int getTime() {
    return time;
  }

  public void setContent(final String template, final Pet pet) {
    this.content = template + Constants.SPACE + Constants.HASH_TAG + pet.getType() + Constants.SPACE
        + Constants.HASH_TAG + pet.getName();
  }

  public String getContent() {
    return content;
  }
}
