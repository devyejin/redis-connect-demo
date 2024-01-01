package com.example.redisconnectdemo.controller;

import com.example.redisconnectdemo.dto.RedisReqDto;
import com.example.redisconnectdemo.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequiredArgsConstructor
@RestController
public class RedisController {


    private final RedisService redisService;

    // KEY, VALUE 저장 API
    @PostMapping("/redis")
    public boolean create(@RequestBody RedisReqDto reqDto) {
        log.info("reqDto={}",reqDto);
        redisService.setRedisValue(reqDto.getKey(),reqDto.getValue());
        return true;
    }

    //KEY로 VALUE값 조회 API
    @GetMapping("/redis")
    public String read(@RequestParam String key) {
        return redisService.getRedisValue(key);
    }

    @PutMapping("/redis")
    public boolean update(@RequestBody RedisReqDto reqDto) {
        redisService.updateRedisValue(reqDto.getKey(), reqDto.getValue());
        return true;
    }

    @DeleteMapping("/redis")
    public boolean delete(@RequestBody RedisReqDto reqDto) {
        redisService.deleteRedisValue(reqDto.getKey());
        return true;
    }
}
