package sample.web.ui.repository;

import org.springframework.data.repository.CrudRepository;
import sample.web.ui.domain.BaseOrder;
import sample.web.ui.domain.Order;

/**
 * Created by bram_ on 22-2-2017.
 */
public interface BaseOrderRepository extends CrudRepository<BaseOrder, Long> {
    // Standaard hebben we heel de CRUD via de CrudRepository.
}