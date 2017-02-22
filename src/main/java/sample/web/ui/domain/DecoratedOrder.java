package sample.web.ui.domain;

import javax.persistence.*;

/**
 * Created by bram_ on 22-2-2017.
 */
@Entity
@DiscriminatorColumn(name = "type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class DecoratedOrder extends BaseOrder {

    @OneToOne(cascade = javax.persistence.CascadeType.REMOVE)
    protected BaseOrder baseOrder;

    protected DecoratedOrder(BaseOrder baseOrder) {
        this.baseOrder = baseOrder;
    }


    public abstract int price();

}
