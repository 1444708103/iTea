package top.season66.itea.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.joda.money.Money;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItem implements Serializable {
    private static final long serialVersionUID = 8585684450527309518L;

    private Long id;

    private String name;


    private Size size;

    private BigDecimal price;


    private Date createTime;


    private Date updateTime;
}

