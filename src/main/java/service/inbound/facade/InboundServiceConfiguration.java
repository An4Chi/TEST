package service.inbound.facade;

import common.BaseFacade;
import common.ObjectMapperConfiguration;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InboundServiceConfiguration {
    @Resource
    private BaseFacade baseFacade;
    @Resource
    private ObjectMapperConfiguration objectMapperConfiguration;

    @Bean
    public InboundConfiguration inboundConfiguration() {
        return new InboundConfiguration();
    }

    @Bean
    public InboundFacade inboundFacade(final InboundConfiguration inboundConfiguration) {
        return new InboundFacade(baseFacade, objectMapperConfiguration, inboundConfiguration);
    }
}
