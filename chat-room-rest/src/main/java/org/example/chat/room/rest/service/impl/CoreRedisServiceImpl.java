package org.example.chat.room.rest.service.impl;

import com.alibaba.fastjson.JSON;
import org.example.chat.common.RedisConstant;
import org.example.chat.common.ws.UserInfo;
import org.example.chat.room.rest.service.CoreRedisService;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CoreRedisServiceImpl implements CoreRedisService {

    @Resource
    private RedissonClient redissonClient;

    @Override
    public void setUserInfo(String token, UserInfo userInfo) {
        RBucket<String> bucket = redissonClient.getBucket(String.format(RedisConstant.TOKEN_USER, token));
        bucket.set(JSON.toJSONString(userInfo));
    }
}
