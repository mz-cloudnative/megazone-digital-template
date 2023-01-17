package com.megazone.springbootbackend.sample.repository.mapper;

import com.megazone.springbootbackend.sample.model.entity.SampleJpaEntity;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/***************************************************
 * <ul>
 * <li>업무 그룹명 : DB 접근 처리</li>
 * <li>서브 업무명 : Mybatis 어노테이션 예제</li>
 * <li>설       명 : </li>
 * <li>작   성  자 : mz01-ohyunbk</li>
 * <li>작   성  일 : 2023/01/16 11:29 AM</li>
 * </ul>
 * <pre>
 * ======================================
 * 변경자/변경일 :
 * 변경사유/내역 :
 * ======================================
 * </pre>
 ***************************************************/
@Mapper
public interface SampleMapper {
  /**
   * @apiNote sample 테이블 저장
   *
   * @since 2023/01/13 1:37 PM
   * @author mz01-ohyunbk
   * @param requestToEntity: 저장 할 엔티티
   */
  @Insert("insert into sample (id, name, reg_dtt) values (#{sampleId},#{sampleName},#{sampleRegDtt})")
  void save(SampleJpaEntity requestToEntity);

  /**
   * @apiNote sample 테이블의 모든 데이터 조회
   *
   * @since 2023/01/16 11:29 AM
   * @author mz01-ohyunbk
   * @return sample 테이블 조회 결과
   */
  @Select("select * from sample")
  @Results(id = "sampleMap", value = {
      @Result(property = "sampleId", column = "id"),
      @Result(property = "sampleName", column = "name"),
      @Result(property = "sampleRegDtt", column = "reg_dtt")
  })
  List<SampleJpaEntity> findAll();

  /**
   * @apiNote sample 테이블 특정 ID 조회
   *
   * @since 2023/01/16 11:29 AM
   * @author mz01-ohyunbk
   * @param sampleId: 특정 ID
   * @return sample 테이블 해당 ID 결과
   */
  @Select("select * from sample where id = #{id}")
  @ResultMap("sampleMap")
  Optional<SampleJpaEntity> findById(@Param("id") Long sampleId);

}
