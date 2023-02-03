package nl.hu.inno.thuusbezorgd.delivery;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DeliveryApplication {
    public static void main(String[] args) {
        SpringApplication.run(nl.hu.inno.thuusbezorgd.order.OrdersApplication.class, args);
    }
}
