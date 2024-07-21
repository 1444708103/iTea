package top.season66.itea.service;

import top.season66.itea.model.MenuItem;

import java.util.List;
import java.util.Optional;

public interface MenuService {

    public List<MenuItem> getAllMenu();
    public List<MenuItem> getByIdList(List<Long> ids);

    List<MenuItem> getByName(String name);

    Optional<MenuItem> getByid(Long id);

    Optional<Integer> save(MenuItem item);

    int saveBatch(List<MenuItem> items);
}
