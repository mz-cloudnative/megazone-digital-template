package com.megazone.springbootbackend.moim.model.domain;

public enum Part {
  MOIM("MOIM"),   // 모임
  STUDYROOM("STUDYROOM"), // 스터디룸
  FREE("FREE"); // 프리

  String part;

  Part(String part) {
    this.part = part;
  }

  public static Part valueOfPart(final String part) {
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
