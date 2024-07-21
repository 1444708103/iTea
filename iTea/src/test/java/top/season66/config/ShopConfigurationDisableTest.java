package top.season66.config;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import top.season66.itea.ITeaApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(classes = ITeaApplication.class, properties = {
        "itea.ready=false"
})
public class ShopConfigurationDisableTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void testPropertiesBeanUnavailable() {
        assertEquals("false",
                applicationContext.getEnvironment().getProperty("itea.ready"));
        assertFalse(applicationContext
                .containsBean("top.season66.iTea.iTeaProperties"));
    }

//    @Test
//    void testIndicatorDown() {
//        ShopReadyHealthIndicator indicator = applicationContext.getBean(ShopReadyHealthIndicator.class);
//        assertEquals(Status.DOWN, indicator.getHealth(false).getStatus());
//    }
}
