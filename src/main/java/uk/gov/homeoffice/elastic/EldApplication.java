package uk.gov.homeoffice.elastic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class EldApplication {

    /**
     * The main application
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(EldApplication.class, args);
    }
}
