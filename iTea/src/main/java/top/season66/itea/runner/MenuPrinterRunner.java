package top.season66.itea.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import top.season66.itea.model.MenuItem;
import top.season66.itea.repository.MenuItemMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@Order(2)
public class MenuPrinterRunner implements ApplicationRunner {

    @Autowired
    private RedisTemplate redisTemplate;

    //注解注入
    @Autowired
    private  MenuItemMapper menuItemMapper;

    // 构造器注入
//    private final MenuItemMapper menuItemMapper;
//
//    public MenuPrinterRunner(MenuItemMapper menuItemMapper) {
//        this.menuItemMapper = menuItemMapper;
//    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        //加载菜单项
        List<MenuItem> itemList = null;

        //先从缓存找
        if (redisTemplate.hasKey("itea-menu")){
            BoundListOperations<String, MenuItem> boundListOps = redisTemplate.boundListOps("itea-menu");
            itemList = boundListOps.range(0, -1);
            log.info("从缓存获取item-list");
        }else {
        //再从数据库找
            itemList=menuItemMapper.findAll();
            log.info("从数据获取item-list");
            redisTemplate.opsForList().leftPushAll("itea-menu",itemList);
            log.info("随后把item-list加入缓存");
        }



        log.info("共有{}个饮品可选。", itemList.size());
        itemList.forEach(i -> log.info("饮品：{}", i));
    }
}
