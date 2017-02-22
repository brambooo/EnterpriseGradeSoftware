package sample.web.ui.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by bram_ on 22-2-2017.
 */
@Entity
@DiscriminatorValue(value = "OrderOption")
public class OrderOption extends DecoratedOrder {
    // Attributes
    private String name;
    private int price;

    public OrderOption(String name, int price, BaseOrder baseOrder) {
        super(baseOrder);
        this.name = name;
        this.price = price;
    }

    public int price() {
        return price;
    }

    @Override
    public String toString() {
        return "Order optie info: " + super.toString();
    }

}
