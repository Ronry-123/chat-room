package org.example.chat.room.ws;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class RedissonConfig {

    @Value("${spring.redis.host:127.0.0.1}")
    private String redisHost;
    @Value("${spring.redis.port:6379}")
    private String redisPort;

    @Value("${spring.redis.database:0}")
    private String redisDb;

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setDatabase(Integer.parseInt(redisDb))
                .setAddress("redis://" + redisHost + ":" + redisPort);

        return Redisson.create(config);
    }
}