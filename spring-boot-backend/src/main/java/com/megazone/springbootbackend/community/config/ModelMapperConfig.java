package com.megazone.springbootbackend.community.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***************************************************
 * <ul>
 * <li>업무 그룹명 : </li>
 * <li>서브 업무명 : </li>
 * <li>파  일  명 : ModelMapperConfig</li>
 * <li>작  성  자 : mz01-ohyunbk</li>
 * <li>작  성  일 : 2023/01/27</li>
 * <li>설     명 : </li>
 * </ul>
 * <pre>
 * ======================================
 * 작성자             일시                  내용
 * mz01-ohyunbk    2023/01/27 3:02 PM    최초 생성
 * ======================================
 * </pre>
 ***************************************************/
@Configuration
public class ModelMapperConfig {

  private final ModelMapper modelMapper = new ModelMapper();
  @Bean
  public ModelMapper modelMapper(){
    modelMapper.getConfiguration()
        //매핑전략 종류는 크게 3가지. STANDARD, STRICT, LOOSE
        .setMatchingStrategy(MatchingStrategies.STRICT)
        //Null 필드 SKIP
        .setSkipNullEnabled(true)
        //Tokenizer 설정
        //.setSourceNameTokenizer(NameTokenizers.CAMEL_CASE)
        //.setDestinationNameTokenizer(NameTokenizers.UNDERSCORE)
    ;

    return modelMapper;
  }

}
