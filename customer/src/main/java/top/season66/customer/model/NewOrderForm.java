package top.season66.customer.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewOrderForm {
    private List<String> itemIdList;
    private int discount;
}
