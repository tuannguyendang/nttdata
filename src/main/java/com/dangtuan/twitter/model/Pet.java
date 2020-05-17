package com.dangtuan.twitter.model;

import com.dangtuan.twitter.constants.PETTYPE;

public class Pet {

  private int id;
  private String name;
  private PETTYPE type;

  public Pet(String name, PETTYPE type) {
    this.name = name;
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public PETTYPE getType() {
    return type;
  }

  public int getId() {
    return id;
  }
}
