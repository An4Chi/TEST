package common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"common"})
public class BaseConfiguration {
    @Bean
    public ObjectMapperConfiguration objectMapperConfiguration() {
        return new ObjectMapperConfiguration();
    }

    @Bean
    public BaseFacadeEntity baseFacadeEntity() {
        return new BaseFacadeEntity();
    }

    @Bean
    public BaseFacade baseFacade() {
        return new BaseFacade(baseFacadeEntity(), objectMapperConfiguration());
    }
}
