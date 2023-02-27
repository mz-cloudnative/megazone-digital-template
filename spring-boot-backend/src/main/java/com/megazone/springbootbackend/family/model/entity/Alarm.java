package com.megazone.springbootbackend.family.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/***************************************************
 * <ul>
 * <li>업무 그룹명 : </li>
 * <li>서브 업무명 : </li>
 * <li>파  일  명 : alarm</li>
 * <li>작  성  자 : mz01-ohyunbk</li>
 * <li>작  성  일 : 2023/02/01</li>
 * <li>설     명 : </li>
 * </ul>
 * <pre>
 * ======================================
 * 작성자             일시                  내용
 * mz01-ohyunbk    2023/02/01 11:00 AM    최초 생성
 * ======================================정
 * </pre>
 ***************************************************/
@AllArgsConstructor
@Getter
public enum Alarm {
  EMAIL("e"),
  SMS("s");

  private final String code;
}
