package transcation_learing.transcation_learing.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import transcation_learing.transcation_learing.domain.Customer;

/**
 * @author Grubby
 * @date 2019/01/01
 */
public interface CostomerRepositry extends JpaRepository<Customer,Long>{

    Customer findOneByUsername(String username);
}
