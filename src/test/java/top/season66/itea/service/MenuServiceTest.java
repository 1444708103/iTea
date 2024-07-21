package top.season66.itea.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import top.season66.debug.BeanDebugger;
import top.season66.debug.CacheDebugger;
import top.season66.debug.RedisCacheDebugger;
import top.season66.itea.model.MenuItem;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MenuServiceTest {


    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testFindBeanDefinition() {
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
        String[] allBeanNames = configurableApplicationContext.getBeanDefinitionNames();
        for(String beanName : allBeanNames) {
            if(beanName.equals("menuService")) {
                System.out.println("Bean name: " + beanName);
                System.out.println("Bean definition: " + configurableApplicationContext.getBeanFactory().getBeanDefinition(beanName));
            }
        }
    }
    //    @Qualifier("menuServiceImpl")
    @Qualifier("menuServiceImpl")
    @Autowired
    private MenuService menuService;

    @Autowired
    private RedisCacheDebugger redisCacheDebugger;

    @Autowired
    private CacheDebugger cacheDebugger;

    @Autowired
    private BeanDebugger beanDebugger;
    @Test
    public void testCache() {


        // First call - should trigger the actual method and cache the result
        List<MenuItem> menu1 = menuService.getAllMenu();
        System.out.println("First call: " + menu1);

        // Second call - should return the cached result
        List<MenuItem> menu2 = menuService.getAllMenu();
        System.out.println("Second call: " + menu2);

        // Print cache keys
        cacheDebugger.printCacheKeys("menu");

        // Print Redis cache keys
        redisCacheDebugger.printCacheKeys();

        // Print all cache-related beans
        beanDebugger.printCacheBeans();
    }
}
