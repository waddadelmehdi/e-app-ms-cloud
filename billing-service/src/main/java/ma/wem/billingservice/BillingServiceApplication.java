package ma.wem.billingservice;

import ma.wem.billingservice.entities.Bill;
import ma.wem.billingservice.entities.ProductItem;
import ma.wem.billingservice.feign.CustomerRestClient;
import ma.wem.billingservice.feign.ProductRestClient;
import ma.wem.billingservice.model.Customer;
import ma.wem.billingservice.model.Product;
import ma.wem.billingservice.repositories.BillRepository;
import ma.wem.billingservice.repositories.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@EnableFeignClients
@SpringBootApplication
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BillRepository billRepository,
                                        ProductItemRepository productItemRepository,
                                        CustomerRestClient customerRestClient,
                                        ProductRestClient productRestClient
                                        ) {
        return args -> {
            Collection<Customer> customers=customerRestClient.getAllCustomers().getContent();
            Collection<Product> products=productRestClient.getAllProducts().getContent();

            customers.forEach(c->{
                Bill bill = Bill.builder()
                        .billingDate(new Date())
                        .customerId(c.getId())
                        .build();
                billRepository.save(bill);
                products.forEach(p->{
                    ProductItem productItem=ProductItem.builder()
                            .bill(bill)
                            .productId(p.getId())
                            .quantity(1+new Random().nextInt(10))
                            .unitPrice(p.getPrice())
                            .build();
                    productItemRepository.save(productItem);
                });
            });

        };

    }

}
