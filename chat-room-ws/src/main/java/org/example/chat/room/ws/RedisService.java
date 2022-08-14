package org.example.chat.room.ws;

import com.alibaba.fastjson.JSON;
import org.example.chat.common.RedisConstant;
import org.example.chat.common.ws.UserInfo;
import org.redisson.api.RBucket;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RedisService {

    @Resource
    private RedissonClient redissonClient;

    public UserInfo getUserInfo(String token) {
        RBucket<String> rBucket = redissonClient.getBucket(String.format(RedisConstant.TOKEN_USER, token));
        return JSON.parseObject(rBucket.get(), UserInfo.class);
    }

    public List<Long> getRoomUidList(Long roomId) {
        RSet<Long> rSet = redissonClient.getSet(String.format(RedisConstant.ROOM_USER_SET, roomId));
        return rSet.readAll().stream().collect(Collectors.toList());
    }
}
