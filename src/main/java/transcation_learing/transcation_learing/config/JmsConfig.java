package transcation_learing.transcation_learing.config;

import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import javax.jms.ConnectionFactory;

/**
 * JMS 配置类
 * 使用外部管理事务 JmsTransactionManager
 * @author Grubby
 * @date 2019/01/02
 */
/**
 * @EnableJms
 * 使用jms默认配置
 */
@EnableJms
@Configuration
public class JmsConfig {

    @Bean
    public PlatformTransactionManager transactionManager(ConnectionFactory cf){
        return new JmsTransactionManager(cf);
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory cf){
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(cf);
        return jmsTemplate;
    }

    /**
     * 这个用于设置 @JmsListener使用的containerFactory
     */
    @Bean
    public JmsListenerContainerFactory<?> msgFactory(ConnectionFactory connectionFactory,
                                                     DefaultJmsListenerContainerFactoryConfigurer configurer,
                                                     PlatformTransactionManager transactionManager) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setTransactionManager(transactionManager);
        factory.setCacheLevelName("CACHE_CONNECTION");
        factory.setReceiveTimeout(10000L);
        configurer.configure(factory, connectionFactory);
        return factory;
    }

}
