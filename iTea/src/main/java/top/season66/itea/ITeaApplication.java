package top.season66.itea;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import top.season66.itea.actuator.SalesMetrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
@MapperScan("top.season66.itea.repository")
@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = "top.season66")

public class ITeaApplication {

    private static Logger logger = LoggerFactory.getLogger(ITeaApplication.class);
    private Random random = new Random();
    @Autowired
    private SalesMetrics salesMetrics;

    public static void main(String[] args) {
        SpringApplication.run(ITeaApplication.class, args);
    }


    // 模拟客户下单
//    @Scheduled(fixedRate = 5000, initialDelay = 1000)
//    public void periodicallyMakeAnOrder() {
//        int amount = random.nextInt(100);
//        salesMetrics.makeNewOrder(amount);
//        logger.info("Make an order of RMB {} yuan.", amount);
//    }




}


