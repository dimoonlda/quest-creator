package ua.kiev.dimoon.questcreator.quest.service.impl.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = {
        "ua.kiev.dimoon.questcreator.quest.service.impl",
},
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Configuration.class})
)
public class QuestServiceConfig {
}
