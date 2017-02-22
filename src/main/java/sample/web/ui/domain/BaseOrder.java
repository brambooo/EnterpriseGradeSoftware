package sample.web.ui.domain;

import javax.persistence.*;

/**
 * Created by bram_ on 22-2-2017.
 */
@Entity
@DiscriminatorColumn(name = "type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class BaseOrder {

    @Id
    @GeneratedValue
    protected Long id;

    public abstract int price();
}

