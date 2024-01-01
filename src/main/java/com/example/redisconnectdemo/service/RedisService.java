package com.example.redisconnectdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class RedisService {

    private final int LIMIT_TIME = 3 * 60; //3분

    private final StringRedisTemplate stringRedisTemplate;


    /*
    (궁금해서)
    opsForValue()
        - 스프링 프레임웤이 제공하는 StringRedisTemplate클래스의 메서드
        - Redis의 문자열 값을 다루기 위한 연산을 수행할 때 사용
        - ValueOperations<K, V> 인터페이스를 반환, K : KEY, V : VALUE
        - 즉, Redis에서 문자열 키와 문자열 값에 대한 연산을 수행하는 메서드 제공
     */

    //create
    public boolean setRedisValue(String key, String value) {
        ValueOperations<String,String> stringValueOperations = stringRedisTemplate.opsForValue();
        stringValueOperations.set(key,value); //만료시간 없는 경우 (Redis-cli에서의 set명령어와 동일)
//        stringValueOperations.set(key,value,LIMIT_TIME); //만료시간이 있는 경우
        return true;
    }

    //read
    public String getRedisValue(String key) {
        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
        String value = stringValueOperations.get(key);// Redis-cli의 get메서드와 동일한 역할

        if(value == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST); // VALUE가 없는 경우 400에러 반환
        }

        return value;
    }

    //update
    public void updateRedisValue(String key, String value) {
        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
        stringValueOperations.getAndSet(key,value); //세부적으로 로직을 안짜서, 없는 key면 key를 생성해서 저장하네, 이런거 예외처리 필요할 듯

    }

    //delete
    public void deleteRedisValue(String key) {
        stringRedisTemplate.delete(key);
    }
}
