package transcation_learing.transcation_learing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import transcation_learing.transcation_learing.dao.CostomerRepositry;
import transcation_learing.transcation_learing.domain.Customer;

import java.util.List;

/**
 * 使用注解来开启事物
 * @author Grubby
 * @date 2019/01/01
 */
@Service
public class CostomerService {

    @Autowired
    private CostomerRepositry costomerRepositry;

    @Transactional
    public Customer create(Customer customer){
        return costomerRepositry.save(customer);
    }

    public List<Customer> getAllCustomer(){
        return costomerRepositry.findAll();
    }

}
