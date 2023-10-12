package service.auth;

import common.BaseConfiguration;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"common", "service.auth"})
public class AuthServiceConfiguration {
    @Resource
    private BaseConfiguration baseConfiguration;

    @Bean
    public AuthServiceEntity authConfiguration() {
        return new AuthServiceEntity();
    }

    @Bean
    public AuthServiceFacade authServiceFacade() {
        return new AuthServiceFacade(baseConfiguration.baseFacade(), authConfiguration(), baseConfiguration.objectMapperConfiguration());
    }
}
