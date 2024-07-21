package learning.spring.customer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.money.Money;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
public class MenuItem {
    private Long id;
    private String name;
    private String size;
    private BigDecimal price;
    private Date createTime;
    private Date updateTime;
}
