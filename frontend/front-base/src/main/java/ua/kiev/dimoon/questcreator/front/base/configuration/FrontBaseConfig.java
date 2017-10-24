package ua.kiev.dimoon.questcreator.front.base.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import ua.kiev.dimoon.questcreator.common.modelmapper.configuration.ModelMapperConfig;

@Configuration
@ComponentScan(basePackages = {
        "ua.kiev.dimoon.questcreator.front.base"
},
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class)
)
@Import({
        ModelMapperConfig.class
})
public class FrontBaseConfig {
}
