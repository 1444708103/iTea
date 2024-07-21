package top.season66.debug;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class RedisCacheDebugger {

    @Autowired
    private RedisTemplate redisTemplate;

    public void printCacheKeys() {
        Set<String> keys = redisTemplate.keys("*");
        System.out.println("Redis cache keys:");
        if (keys != null) {
            System.out.println(" keys are found.");
            System.out.println(keys.toString());
            keys.forEach(key -> System.out.println("Key: " + key));
        } else {
            System.out.println("No keys found.");
        }
    }
}
