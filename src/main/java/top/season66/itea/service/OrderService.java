package top.season66.itea.service;

import top.season66.itea.model.Amount;
import top.season66.itea.model.MenuItem;
import top.season66.itea.model.Order;
import top.season66.itea.model.OrderStatus;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;


public interface OrderService {


    public List<Order> getAllOrder();



    public int createOrder(List<MenuItem> itemList, int discount);
}


