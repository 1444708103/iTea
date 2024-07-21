package top.season66.itea.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import top.season66.itea.model.MenuItem;
import top.season66.itea.repository.MenuItemMapper;
import top.season66.itea.service.MenuService;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "menu")
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuItemMapper mapper;
    @Override
    @Cacheable
    public List<MenuItem> getAllMenu() {
        return mapper.findAll();
    }

    @Override
    public List<MenuItem> getByIdList(List<Long> ids) {
        return mapper.findByIdList(ids);
    }

    @Override
    public List<MenuItem> getByName(String name) {
        return mapper.findByName(name);

    }

    @Override
    public Optional<MenuItem> getByid(Long id) {
        return Optional.ofNullable(mapper.findById(id));
    }

    @Override
    public Optional<Integer> save(MenuItem item) {
        return Optional.ofNullable(mapper.save(item));
    }
    @Override
    public int saveBatch(List<MenuItem> items){
        return mapper.saveAll(items);
    }

}
