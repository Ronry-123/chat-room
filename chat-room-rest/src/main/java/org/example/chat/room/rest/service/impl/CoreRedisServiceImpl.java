package org.example.chat.room.rest.service.impl;

import com.alibaba.fastjson.JSON;
import org.example.chat.common.RedisConstant;
import org.example.chat.common.ws.UserInfo;
import org.example.chat.room.rest.service.CoreRedisService;
import org.redisson.api.RBucket;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class CoreRedisServiceImpl implements CoreRedisService {

    @Resource
    private RedissonClient redissonClient;

    @Override
    public void setUserInfo(String token, UserInfo userInfo) {
        RBucket<String> bucket = redissonClient.getBucket(String.format(RedisConstant.TOKEN_USER, token));
        bucket.set(JSON.toJSONString(userInfo), 10, TimeUnit.MINUTES);
    }

    @Override
    public void setRoomMember(String roomId, Set<Long> uids) {
        RSet<Long> rSet = redissonClient.getSet(String.format(RedisConstant.ROOM_USER_SET, roomId));
        rSet.addAll(uids);
    }

    @Override
    public void addRoomMember(String roomId, Long uid) {
        RSet<Long> rSet = redissonClient.getSet(String.format(RedisConstant.ROOM_USER_SET, roomId));
        rSet.add(uid);
    }

    @Override
    public void removeRoomMember(String roomId, Long uid) {
        RSet<Long> rSet = redissonClient.getSet(String.format(RedisConstant.ROOM_USER_SET, roomId));
        rSet.remove(uid);
    }

    @Override
    public void removeAllRoomMember(String roomId) {
        RSet<Long> rSet = redissonClient.getSet(String.format(RedisConstant.ROOM_USER_SET, roomId));
        rSet.clear();
    }
}
