package com.megazone.springbootbackend.family.model.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/***************************************************
 * <ul>
 * <li>업무 그룹명 : </li>
 * <li>서브 업무명 : </li>
 * <li>파  일  명 : AlarmAttributeConverter</li>
 * <li>작  성  자 : mz01-ohyunbk</li>
 * <li>작  성  일 : 2023/02/02</li>
 * <li>설     명 : </li>
 * </ul>
 * <pre>
 * ======================================
 * 작성자             일시                  내용
 * mz01-ohyunbk    2023/02/02 1:51 PM    최초 생성
 * ======================================
 * </pre>
 ***************************************************/
@Converter(autoApply = true)
public class AlarmAttributeConverter implements AttributeConverter<Alarm, String> {

  @Override
  public String convertToDatabaseColumn(Alarm attribute) {
    if (Alarm.EMAIL.equals(attribute)) {
      return Alarm.EMAIL.getCode();
    }
    return Alarm.SMS.getCode();
  }

  @Override
  public Alarm convertToEntityAttribute(String dbData) {
    if (Alarm.EMAIL.getCode().equals(dbData)) {
      return Alarm.EMAIL;
    }
    return Alarm.SMS;
  }
}
