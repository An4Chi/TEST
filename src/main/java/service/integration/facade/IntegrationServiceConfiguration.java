package service.integration.facade;

import common.BaseFacade;
import common.ObjectMapperConfiguration;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IntegrationServiceConfiguration {
    @Resource
    private BaseFacade baseFacade;
    @Resource
    private ObjectMapperConfiguration objectMapperConfiguration;

    @Bean
    public IntegrationManagerConfiguration integrationConfiguration() {
        return new IntegrationManagerConfiguration();
    }

    @Bean
    public IntegrationServiceFacade integrationServiceFacade(final IntegrationManagerConfiguration integrationConfiguration) {
        return new IntegrationServiceFacade(baseFacade, objectMapperConfiguration, integrationConfiguration);
    }
}
