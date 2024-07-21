package top.season66.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import top.season66.itea.ITeaProperties;

// 为了演示自动配置的加载，故意不放在top.season66.binarytea包里
@Configuration
@EnableConfigurationProperties(ITeaProperties.class)
@ConditionalOnProperty(name = "itea.ready", havingValue = "true")
public class ShopConfiguration {
}