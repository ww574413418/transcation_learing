package transcation_learing.transcation_learing.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;
import transcation_learing.transcation_learing.domain.Customer;
import transcation_learing.transcation_learing.service.CostomerService;
import transcation_learing.transcation_learing.service.CostomerServiceInCode;
import transcation_learing.transcation_learing.service.CostomerServiceJMS;

import java.util.List;

/**
 * spring自带事务
 * @author Grubby
 * @date 2019/01/01
 */
@RestController
@RequestMapping("/api/customer")
public class CustomerResource {

    @Autowired
    private CostomerService costomerService;

    @Autowired
    private CostomerServiceInCode costomerServiceInCode;


    @PostMapping("/code")
    public Customer createCustomer(Customer customer){
        return costomerServiceInCode.create(customer);
    }

    @PostMapping("/annotation")
    public Customer createCustomer2(Customer customer){
        return costomerService.create(customer);
    }

    @GetMapping("/getall")
    public List<Customer> getAll(){
        return costomerService.getAllCustomer();
    }

}
