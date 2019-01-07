package transcation_learing.transcation_learing.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Grubby
 * @date 2019/01/01
 */
@Data
@ToString
@Entity(name = "customer")
public class Customer {

    @Id
    @GeneratedValue
    private long id;

    /**
     * unique 唯一索引,创建的时候不会重复
     */
    @Column(name = "user_name",unique = true)
    private String username;

    private String password;

    private String role;

}
