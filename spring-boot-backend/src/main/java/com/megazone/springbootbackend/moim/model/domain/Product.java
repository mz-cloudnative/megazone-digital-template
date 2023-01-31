package com.megazone.springbootbackend.moim.model.domain;

public enum Product {
  MOIM("MOIM"),   // 모임
  STUDYROOM("STUDYROOM"), // 스터디룸
  FREE("FREE"); // 프리

  String part;

  Product(String part) {
    this.part = part;
  }

  public static Product valueOfProduct(final String part) {
    for (var value : values()) {
      if (value.part.equals(part)) {
        return value;
      }
    }
    return null;
  }

  public String getPart() {
    return part;
  }
}
