package com.megazone.springbootbackend.family.repository;

import com.megazone.springbootbackend.family.model.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/***************************************************
 * <ul>
 * <li>업무 그룹명 : </li>
 * <li>서브 업무명 : </li>
 * <li>파  일  명 : EventRepository</li>
 * <li>작  성  자 : mz01-ohyunbk</li>
 * <li>작  성  일 : 2023/02/01</li>
 * <li>설     명 : </li>
 * </ul>
 * <pre>
 * ======================================
 * 작성자             일시                  내용
 * mz01-ohyunbk    2023/02/01 1:16 PM    최초 생성
 * ======================================
 * </pre>
 ***************************************************/
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

}
