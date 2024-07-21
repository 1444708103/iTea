package top.season66.itea.repository;

import top.season66.itea.model.MenuItem;
import top.season66.itea.model.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

    int save(Order order);

    int addOrderItem(Long orderId, MenuItem item);

    Order findById(Long id);

    List<Order> findByMakerId(Long makerId);

    List<Order> findAll();
}
