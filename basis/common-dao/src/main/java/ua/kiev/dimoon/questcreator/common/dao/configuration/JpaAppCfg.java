package ua.kiev.dimoon.questcreator.common.dao.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by dlutai on 08.09.17.
 */
@Configuration
@ComponentScan(basePackages = {
        "ua.kiev.dimoon.questcreator.common.dao.jpa",
},
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Configuration.class})
)
@EnableJpaRepositories(basePackages = {"ua.kiev.dimoon.questcreator.common.dao.jpa.repository"})
@EntityScan(basePackages = {"ua.kiev.dimoon.questcreator.common.dao.jpa.entity"})
public class JpaAppCfg {
}
