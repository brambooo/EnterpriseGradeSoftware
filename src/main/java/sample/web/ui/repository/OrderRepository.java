package sample.web.ui.repository;

import org.springframework.data.repository.CrudRepository;
import sample.web.ui.domain.Order;

/**
 * Created by bram_ on 15-2-2017.
 */
public interface OrderRepository extends CrudRepository<Order, Long> {
    // Standaard hebben we heel de CRUD via de CrudRepository.
}

