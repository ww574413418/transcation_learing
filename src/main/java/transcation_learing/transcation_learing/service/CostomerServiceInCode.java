package transcation_learing.transcation_learing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import transcation_learing.transcation_learing.dao.CostomerRepositry;
import transcation_learing.transcation_learing.domain.Customer;

/**
 * 使用代码来实现事物
 * @author Grubby
 * @date 2019/01/01
 */
@Service
public class CostomerServiceInCode {

    @Autowired
    private CostomerRepositry costomerRepositry;

    @Autowired
    private PlatformTransactionManager transactionManager;

    public Customer create(Customer customer){
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        // 设置事务的隔离级别 (默认级别 - 使用数据库的事务隔离级别)
        def.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
        // 设置事务的传播机制 (默认 A开启事务 调用 B 则B不在开启事务)
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        // 开启事务
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            Customer save = costomerRepositry.save(customer);
            // 提交事务
            transactionManager.commit(status);
            return save;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }




}
