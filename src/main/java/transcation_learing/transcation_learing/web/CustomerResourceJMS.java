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
 * 使用 JMS 来管理事务
 * @author Grubby
 * @date 2019/01/01
 */
@RestController
@RequestMapping("/api/customer")
public class CustomerResourceJMS {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private CostomerServiceJMS costomerServiceJMS;

    /**
     * 将前端传入 msg 保存队列的名字
     */
    private String activeMqName_send  ="customer:msg1:new";

    /**
     * 将 msg 做一些处理保存之后 保存的队列名字
     */
    private String activeMqName_relay  ="customer:msg:relay";

    /**
     * 直接使用 JmsTemplate 将消息保存到 activemq
     * 由 CostomerServiceJMS中的 @JmsListener 触发
     * 如果发生错误 会回滚数据
     * @param msg
     */
    @PostMapping("/message1/listen")
    public void create(@RequestParam String msg){
        jmsTemplate.convertAndSend(activeMqName_send,msg);
    }

    /**
     * 使用CostomerServiceJMS 中的 handle 方法触发
     * 将 msg 保存到 activemq
     * 如果发生错误不会回滚数据,因为 事务是由 session来管理 ,
     * 事务只在 jmsTemplate.convertAndSend("customer:msg:relay",relay); 这一行代码起作用
     * @param msg
     */
    @PostMapping("/message1/direct")
    public void handle(@RequestParam String msg){
        costomerServiceJMS.handle(msg);
    }

    /**
     * 获取 activemq 中保存的信息
     * @return
     */
    @GetMapping("/getmessage")
    public String read(){
        jmsTemplate.setReceiveTimeout(2000);
        String result = (String) jmsTemplate.receiveAndConvert(activeMqName_relay);
        return result;
    }
}
