package ma.wem.customerservice;

import ma.wem.customerservice.config.CustomerConfigParams;
import ma.wem.customerservice.entities.Customer;
import ma.wem.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
@EnableConfigurationProperties(CustomerConfigParams.class)
@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
        return args -> {
            customerRepository.save(Customer.builder()
                            .name("Ali").email("ali@gmail.com")
                    .build());
            customerRepository.save(Customer.builder()
                    .name("Karim").email("kr@gmail.com")
                    .build());
            customerRepository.save(Customer.builder()
                    .name("Sara").email("sr@gmail.com")
                    .build());
            customerRepository.save(Customer.builder()
                    .name("Sanae").email("sn@gmail.com")
                    .build());

            customerRepository.findAll().forEach(c->{
                System.out.println("==================");
                System.out.println(c.getId());
                System.out.println(c.getName());
                System.out.println(c.getEmail());
                System.out.println("===================");
            });
        };
    }

}
