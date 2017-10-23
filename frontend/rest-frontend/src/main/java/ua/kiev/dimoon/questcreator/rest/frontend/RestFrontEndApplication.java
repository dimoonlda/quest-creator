package ua.kiev.dimoon.questcreator.rest.frontend;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;
import ua.kiev.dimoon.questcreator.common.dao.configuration.JpaAppCfg;
import ua.kiev.dimoon.questcreator.services.quest.configuration.QuestServiceConfig;

@SpringBootApplication
@Import({JpaAppCfg.class, QuestServiceConfig.class})
public class RestFrontEndApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(RestFrontEndApplication.class).web(true).run(args);
    }
}
