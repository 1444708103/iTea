package top.season66.itea.controller.request;

import top.season66.itea.model.Size;
import lombok.Getter;
import lombok.Setter;
import org.joda.money.Money;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class NewMenuItemForm {
    @NotBlank
    private String name;
    @NotNull
    private BigDecimal price;
    @NotNull
    private Size size;
}
