package top.season66.itea.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import top.season66.itea.model.MenuItem;
import top.season66.itea.repository.MenuItemMapper;
import top.season66.itea.service.MenuService;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@Order(1)
public class MenuCacheRunner implements ApplicationRunner {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MenuItemMapper menuItemMapper;
    @Autowired
    @Qualifier("menuServiceImpl")
    private MenuService menuService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        //缓存预热（本地）
        List<MenuItem> allMenu = menuService.getAllMenu();
        System.out.println("第一次调用getAllMenu(), 在本地预热了"+allMenu.size()+"条菜单缓存");
        //缓存预热（本地）
        List<MenuItem> allMenu2 = menuService.getAllMenu();
        System.out.println("第2次调用getAllMenu(),应该直接在本地缓存读取"+allMenu.size()+"条菜单缓存");

        //先从缓存找
        if (redisTemplate.hasKey("itea-menu")){
//            redisTemplate.delete("itea-menu");
        }else {
            List<MenuItem> itemList = menuItemMapper.findAll();
            log.info("Load {} MenuItems from DB, ready to cache.", itemList.size());
            redisTemplate.opsForList().leftPushAll("itea-menu",itemList);
            redisTemplate.expire("itea-menu",300, TimeUnit.SECONDS);
        }


    }
}
