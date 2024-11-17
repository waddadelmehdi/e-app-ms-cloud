package ma.wem.customerservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RefreshScope
public class ConfigTestRestController {
    @Value("${global.params.p1}")
    private String a;
    @Value("${global.params.p2}")
    private String b;

    @Autowired
    private CustomerConfigParams customerConfigParams;

    @GetMapping("/testConfig1")
    public Map<String,String> configTest(){
        return Map.of("a",a,"b",b);
    }

    @GetMapping("/testConfig2")
    public CustomerConfigParams configTest2(){
        return customerConfigParams;
    }
}


