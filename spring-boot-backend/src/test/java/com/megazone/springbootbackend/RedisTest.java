package com.megazone.springbootbackend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void testString() {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String key = "key";

        valueOperations.set(key, "hello");

        String value = valueOperations.get(key);
        assertThat(value).isEqualTo("hello");
    }

    @Test
    void testSet() {
        SetOperations<String, String> setOperations = redisTemplate.opsForSet();
        String key = "setKey";

        setOperations.add(key, "s","u","c","c","e","s","s","f","u","l");

        Set<String> members = setOperations.members(key);
        Long size = setOperations.size(key);

        assertThat(members).containsOnly("s","u","c","e","f","l");
        assertThat(size).isEqualTo(6);
    }

    @Test
    void testHash() {
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        String key = "hashKey";

        hashOperations.put(key, "hello", "world");

        Object value = hashOperations.get(key,"hello");
        assertThat(value).isEqualTo("world");

        Map<Object, Object> entries = hashOperations.entries(key);
        assertThat(entries.keySet()).containsExactly("hello");
        assertThat(entries.values()).containsExactly("world");

        Long size = hashOperations.size(key);
        assertThat(size).isEqualTo(entries.size());
    }

    @Test
    void testList() {
        ListOperations<String, String> listOperations = redisTemplate.opsForList();
        String key = "listKey";

        listOperations.rightPush(key, "hello");
        listOperations.rightPush(key, "world");
        listOperations.rightPush(key, "!");
        listOperations.leftPush(key, "!");

        Long size = listOperations.size(key);
        assertThat(size).isEqualTo(4);

        String first = listOperations.leftPop(key);
        assertThat(first).isEqualTo("!");

        listOperations.set(key, 2, ".");
        String last = listOperations.rightPop(key);
        assertThat(last).isEqualTo(".");
    }
}
