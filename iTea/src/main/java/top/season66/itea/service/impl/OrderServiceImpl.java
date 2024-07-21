package top.season66.itea.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.season66.itea.model.Amount;
import top.season66.itea.model.MenuItem;
import top.season66.itea.model.Order;
import top.season66.itea.model.OrderStatus;
import top.season66.itea.repository.MenuItemMapper;
import top.season66.itea.repository.OrderMapper;
import top.season66.itea.service.OrderService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper mapper;

    @Override
    public List<Order> getAllOrder() {
        return mapper.findAll();
    }

    @Override
    public int createOrder(List<MenuItem> itemList, int discount) {
        // Calculate the total price of all items
        BigDecimal total = itemList.stream()
                .map(MenuItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Calculate the discount amount
        BigDecimal pay = total.multiply(BigDecimal.valueOf(discount).divide(BigDecimal.valueOf(100)));



        // Create the Amount object
        Amount amount = Amount.builder()
                .discount(discount)
                .totalAmount(total)
                .payAmount(pay)
                .build();

        // Create the Order object
        Order order = Order.builder()
                .amount(amount)
                .status(OrderStatus.ORDERED)
                .items(itemList)
                .build();

        // Save and return the Order object
        return mapper.save(order);
    }
}
