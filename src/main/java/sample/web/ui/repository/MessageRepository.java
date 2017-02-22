
package sample.web.ui.repository;

import org.springframework.data.repository.CrudRepository;
import sample.web.ui.domain.Message;

/**
 * @author Rob Winch
 */
public interface MessageRepository extends CrudRepository<Message, Long> {
	// Standaard hebbenw e heel de CRUD via de CrudRepository.
}
