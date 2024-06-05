package top.season66.config;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import top.season66.itea.ITeaApplication;
import top.season66.itea.ITeaProperties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
//, properties = {
//        "itea.ready=true",
//        "itea.open-hours=8:30-22:00"
//        }
@SpringBootTest(classes = ITeaApplication.class)
public class ShopConfigurationEnableTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void testPropertiesBeanAvailable() {
//        assertNotNull(applicationContext.getBean(iTeaProperties.class));
        ITeaProperties bean = applicationContext.getBean(ITeaProperties.class);
        System.out.println("Bean full qualified name: " + bean.getClass().getName());
        assertNotNull(bean);
        // 获取所有的 bean 名字
        String[] beanNames = applicationContext.getBeanNamesForType(ITeaProperties.class);
        // 打印所有的 bean 名字
        for (String beanName : beanNames) {
            System.out.println("Bean name: " + beanName);
        }
        assertTrue(applicationContext
                .containsBean("itea-top.season66.itea.ITeaProperties"));
    }

    @Test
    void testPropertyValues() {
        ITeaProperties properties =
                applicationContext.getBean(ITeaProperties.class);
        assertTrue(properties.isReady());
        assertEquals("8:30-22:00", properties.getOpenHours());
    }

//    @Test
//    void testIndicatorUp() {
//        ShopReadyHealthIndicator indicator = applicationContext.getBean(ShopReadyHealthIndicator.class);
//        assertEquals(Status.UP, indicator.getHealth(false).getStatus());
//    }
}