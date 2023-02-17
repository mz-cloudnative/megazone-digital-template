package com.megazone.springbootbackend.sample.repository.mapper;

import com.megazone.springbootbackend.sample.model.domain.SampleRedis;
import com.megazone.springbootbackend.sample.model.dto.SampleDataResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/***************************************************
 * <ul>
 * <li>업무 그룹명 : </li>
 * <li>서브 업무명 : </li>
 * <li>파  일  명 : RedisSampleMapper</li>
 * <li>작  성  자 : mz01-ohyunbk</li>
 * <li>작  성  일 : 2023/02/17</li>
 * <li>설     명 : </li>
 * </ul>
 * <pre>
 * ======================================
 * 작성자             일시                  내용
 * mz01-ohyunbk    2023/02/17 3:50 PM    최초 생성
 * ======================================
 * </pre>
 ***************************************************/
@Mapper(componentModel = "spring")
public interface RedisSampleMapper {

  RedisSampleMapper INSTANCE = Mappers.getMapper(RedisSampleMapper.class);

  @Mapping(target = "expiration", ignore = true)
  SampleRedis responseToSampleRedis(SampleDataResponse sampleDataResponse);

  SampleDataResponse sampleRedisToResponse(SampleRedis sampleRedis);
}
