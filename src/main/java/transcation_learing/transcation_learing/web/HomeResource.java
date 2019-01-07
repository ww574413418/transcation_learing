package transcation_learing.transcation_learing.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Grubby
 * @date 2018/12/31
 */
@RestController
@RequestMapping("/api")
public class HomeResource {

    @GetMapping("/hello")
    public String helloWorld(){
        return "hello world";
    }

}
