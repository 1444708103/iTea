package top.season66.debug;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class BeanDebugger {

    @Autowired
    private ApplicationContext applicationContext;

    public void printCacheBeans() {
        System.out.println("Printing all beans related to caching:");
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            Object bean = applicationContext.getBean(beanName);
            if (bean instanceof CacheManager || bean instanceof Cache) {
                System.out.println("Bean name: " + beanName + ", Bean type: " + bean.getClass().getName());
            }
        }
    }
}
