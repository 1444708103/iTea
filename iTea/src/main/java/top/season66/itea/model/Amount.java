package top.season66.itea.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Amount {
    private int discount;
    private BigDecimal totalAmount;
    private BigDecimal payAmount;
}
