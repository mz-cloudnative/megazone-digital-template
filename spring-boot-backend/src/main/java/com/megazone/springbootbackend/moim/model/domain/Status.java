package com.megazone.springbootbackend.moim.model.domain;

public enum Status {
  CREATE("CREATE"), // 예약생성
  READY("READY"), // 예약가능
  RESERVE("RESERVE"),   // 예약 중
  EXPIRED("EXPIRED"); // 모임만료

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
