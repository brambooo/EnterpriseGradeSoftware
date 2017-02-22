

package sample.web.ui.domain;


import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


@Entity
@DiscriminatorColumn(name = "type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@Table(name = "Orders") // to prevent MySQL naming collision
public class Order extends BaseOrder {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private ArrayList<Product> orderList;

    // Lijst met producten die bij een order hoort
    @OneToMany(cascade = javax.persistence.CascadeType.ALL, orphanRemoval=true) // TODO: hier OneToMany opdracht 2 ManyToMany
    private List<Product> products = new ArrayList<Product>();


    public boolean add(Product product) {
        return products.add(product);
    }

    @Override
    public int price() {
        int totalPrice = 0;
        for (Product p : products) {
            totalPrice += p.getPrice();
        }
        return totalPrice;
    }
}
