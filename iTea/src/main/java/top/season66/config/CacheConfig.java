package top.season66.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@EnableCaching
public class CacheConfig {
//    @Bean("customKeyGenerator")
//    public KeyGenerator keyGenerator() {
//        return (target, method, params) -> {
//            if (params.length == 0) {
//                return SimpleKey.EMPTY;
//            }
//            StringBuilder sb = new StringBuilder();
//            sb.append(target.getClass().getSimpleName()).append(".");
//            sb.append(method.getName());
//            for (Object param : params) {
//                sb.append(param.toString());
//            }
//            return sb.toString();
//        };
//    }
}
