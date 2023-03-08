package com.megazone.springbootbackend.family.model.event;

import java.time.LocalDateTime;
import lombok.Getter;

/***************************************************
 * <ul>
 * <li>업무 그룹명 : </li>
 * <li>서브 업무명 : </li>
 * <li>파  일  명 : BaseEvent</li>
 * <li>작  성  자 : mz01-ohyunbk</li>
 * <li>작  성  일 : 2023/02/27</li>
 * <li>설     명 : </li>
 * </ul>
 * <pre>
 * ======================================
 * 작성자             일시                  내용
 * mz01-ohyunbk    2023/02/27 3:48 PM    최초 생성
 * ======================================
 * </pre>
 ***************************************************/
@Getter
public abstract class BaseEvent {

  private final LocalDateTime timestamp;

  public BaseEvent() {
    this.timestamp = LocalDateTime.now();
  }
}
