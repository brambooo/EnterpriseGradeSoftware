package sample.web.ui.repository;

import org.springframework.data.repository.CrudRepository;
import sample.web.ui.domain.Product;
import sample.web.ui.domain.ProductCatalog;

/**
 * Created by bram_ on 15-2-2017.
 */
public interface ProductCatalogRepository extends CrudRepository<ProductCatalog, Long>    {
}
