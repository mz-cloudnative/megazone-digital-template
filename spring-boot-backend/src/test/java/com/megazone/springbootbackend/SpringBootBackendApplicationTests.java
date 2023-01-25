package com.megazone.springbootbackend;

import com.megazone.springbootbackend.util.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class SpringBootBackendApplicationTests {

	@Autowired
	RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

	@Test
	void contextLoads() {
	}

	@Test
	public void redisTemplateTest(){
		RedisUtils redisUtils = new RedisUtils(redisTemplate);
		redisUtils.put("test1", "12344", 30L);
		System.out.println("redisUtils.get(\"test\", String.class) = " + redisUtils.get("test1", String.class));

	}

}
