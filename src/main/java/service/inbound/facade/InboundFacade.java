package service.inbound.facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nortecview.config.integration.inbound.entity.IntegrationInboundFlowResponse;
import common.BaseFacade;
import common.ObjectMapperConfiguration;
import common.exceptions.AutomationInterruptedException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import service.inbound.entity.InboundRequestEntity;
import service.inbound.entity.TrackingInboundFlowEntity;

@Slf4j
@Component
public class InboundFacade {
    @Resource
    private final BaseFacade baseFacade;
    @Resource
    private final ObjectMapperConfiguration objectMapperConfiguration;
    @Resource
    private final InboundConfiguration inboundConfiguration;

    @Autowired
    public InboundFacade(BaseFacade baseFacade, ObjectMapperConfiguration objectMapperConfiguration, InboundConfiguration inboundConfiguration) {
        this.baseFacade = baseFacade;
        this.objectMapperConfiguration = objectMapperConfiguration;
        this.inboundConfiguration = inboundConfiguration;
    }

    public Integer triggerInboundJob() {
        return baseFacade.getEmptyBody(inboundConfiguration.getTriggerInboundJobEndpoint()).statusCode();
    }

    public TrackingInboundFlowEntity getInboundTrackingStatus(Long inboundFlowId) {
        String url = String.format(inboundConfiguration.getTriggerInboundTrackingEndpoint(), inboundFlowId);
        return baseFacade.get(url, TrackingInboundFlowEntity.class);
    }

    public IntegrationInboundFlowResponse triggerInboundFlow(InboundRequestEntity inboundRequestEntity) {
        try {
            String body = objectMapperConfiguration.getObjectMapper().writeValueAsString(inboundRequestEntity);
            log.debug("inbound request send successfully");
            return baseFacade.post(inboundConfiguration.getTriggerInboundFlowEndpoint(), body, IntegrationInboundFlowResponse.class);

        } catch (JsonProcessingException e) {
            log.error("could not deserialize send inbound request entity into json");
            throw new AutomationInterruptedException(e.getMessage());
        }
    }
}
