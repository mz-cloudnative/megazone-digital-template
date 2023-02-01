package com.megazone.springbootbackend.sample.repository.mapper;

import com.megazone.springbootbackend.sample.model.entity.SampleJpaEntity;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/***************************************************
 * <ul>
 * <li>업무 그룹명 : DB 접근 처리</li>
 * <li>서브 업무명 : Mybatis xml 예제</li>
 * <li>설       명 : </li>
 * <li>작   성  자 : mz01-ohyunbk</li>
 * <li>작   성  일 : 2023/01/16 12:50 PM</li>
 * </ul>
 * <pre>
 * ======================================
 * 변경자/변경일 :
 * 변경사유/내역 :
 * ======================================
 * </pre>
 ***************************************************/
@Mapper
public interface SampleXmlMapper {
  List<SampleJpaEntity> findByName(@Param("name") String sampleName);
}
