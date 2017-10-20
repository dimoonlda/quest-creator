package ua.kiev.dimoon.questcreator.rest.frontend;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Created by dlutai on 20.10.17.
 */
@SpringBootApplication
public class RestFrontEndApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(RestFrontEndApplication.class).web(true).run(args);
    }
}
