package transcation_learing.transcation_learing.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import transcation_learing.transcation_learing.dao.CostomerRepositry;
import transcation_learing.transcation_learing.domain.Customer;

import java.util.List;

/**
 * 使用 JMS 来管理实务 默认事务由 session来控制
 * @author Grubby
 * @date 2019/01/01
 */
@Slf4j
@Service
public class CostomerServiceJMS {

    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * destination = "customer:msg1:new"  队列的名字
     * @param msg
     */
    @JmsListener(destination = "customer:msg1:new")
    public void handle(String msg){
        log.info("get msg1:{}" ,msg);
        String relay = "Relay-" + msg;
        // 将msg 放入到 customer:msg:relay 队列中
        jmsTemplate.convertAndSend("customer:msg:relay",relay);
        if (msg.contains("error")){
            simulateError();
        }
    }

    private void simulateError(){
        throw new RuntimeException("some data error");
    }

}
