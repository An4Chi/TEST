package service.integration.facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import common.BaseFacade;
import common.ObjectMapperConfiguration;
import common.exceptions.AutomationInterruptedException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import service.integration.entity.FlowTriggerResponseEntity;
import service.integration.entity.IntegrationExecutionFlowEntity;

@PropertySource("classpath:config.properties")
@Slf4j
@Component
public class IntegrationServiceFacade {
    @Resource
    private final BaseFacade baseFacade;
    @Resource
    private final ObjectMapperConfiguration objectMapperConfiguration;
    @Resource
    private final IntegrationManagerConfiguration integrationConfiguration;

    @Autowired
    public IntegrationServiceFacade(BaseFacade baseFacade, ObjectMapperConfiguration objectMapperConfiguration, IntegrationManagerConfiguration integrationConfiguration) {
        this.baseFacade = baseFacade;
        this.objectMapperConfiguration = objectMapperConfiguration;
        this.integrationConfiguration = integrationConfiguration;
    }

    public FlowTriggerResponseEntity triggerIntegrationFlow(IntegrationExecutionFlowEntity integrationExecutionFlowEntity) {
        try {
            String body = objectMapperConfiguration.getObjectMapper().writeValueAsString(integrationExecutionFlowEntity);
            log.debug("Integration request send successfully");
            return baseFacade.post(integrationConfiguration.getTriggerIntegrationFlowEndpoint(), body, FlowTriggerResponseEntity.class);
        } catch (JsonProcessingException e) {
            log.error("could not deserialize send Integration request entity into json");
            throw new AutomationInterruptedException(e.getMessage());
        }
    }

    public Integer triggerOutboundProcess() {
        return baseFacade.getEmptyBody(integrationConfiguration.getOutboundProcessEndpoint()).statusCode();
    }

    public Integer triggerSendMessages() {
        return baseFacade.getEmptyBody(integrationConfiguration.getSendMessagesEndpoint()).statusCode();
    }
}
