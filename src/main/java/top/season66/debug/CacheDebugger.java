package top.season66.debug;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.ConcurrentMap;

@Component
public class CacheDebugger {

    @Autowired
    private CacheManager cacheManager;

    public void printCacheKeys(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            System.out.println("Cache '" + cacheName + "' keys:");
            // Assuming the cache is a ConcurrentMapCache
            if (cache.getNativeCache() instanceof ConcurrentMap) {
                ConcurrentMap<Object, Object> nativeCache = (ConcurrentMap<Object, Object>) cache.getNativeCache();
                nativeCache.keySet().forEach(key -> System.out.println("Key: " + key));
            } else {
                System.out.println("Cache is not an instance of ConcurrentMap, unable to print keys.");
            }
        } else {
            System.out.println("Cache '" + cacheName + "' not found.");
        }
    }

    public void printAllCaches() {
        Collection<String> cacheNames = cacheManager.getCacheNames();
        cacheNames.forEach(cacheName -> {
            System.out.println("Cache: " + cacheName);
            printCacheKeys(cacheName);
        });
    }
}
