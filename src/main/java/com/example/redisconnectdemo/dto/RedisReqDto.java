package com.example.redisconnectdemo.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

@Getter
@Setter
@ToString
public class RedisReqDto {

    private String key;
    private String value;
}
