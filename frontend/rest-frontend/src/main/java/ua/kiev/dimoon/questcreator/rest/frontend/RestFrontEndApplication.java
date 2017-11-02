package ua.kiev.dimoon.questcreator.rest.frontend;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;
import ua.kiev.dimoon.questcreator.common.dao.configuration.JpaAppCfg;
import ua.kiev.dimoon.questcreator.front.base.configuration.FrontBaseConfig;
import ua.kiev.dimoon.questcreator.quest.service.impl.configuration.QuestServiceConfig;
import ua.kiev.dimoon.questcreator.user.service.impl.configuration.UserServiceConfig;

@SpringBootApplication
@Import(
        {
                JpaAppCfg.class,
                QuestServiceConfig.class,
                UserServiceConfig.class,
                FrontBaseConfig.class
        }
)
public class RestFrontEndApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(RestFrontEndApplication.class).web(true).run(args);
    }
}
