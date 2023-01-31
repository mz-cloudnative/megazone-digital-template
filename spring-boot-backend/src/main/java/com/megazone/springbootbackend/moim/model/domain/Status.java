package com.megazone.springbootbackend.moim.model.domain;

public enum Status {
  RESERVE("RESERVE"),   // 예약 중
  NONE("NONE"); // 예약가능

  String staus;

  Status(String staus) {
    this.staus = staus;
  }
  public static Status valueOfStaus(final String staus) {
    for (var value : values()) {
      if (value.staus.equals(staus)) {
        return value;
      }
    }
    return null;
  }

  public String getStaus() {
    return staus;
  }
}
