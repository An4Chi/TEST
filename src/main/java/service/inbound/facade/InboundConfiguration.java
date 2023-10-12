package service.inbound.facade;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@NoArgsConstructor
public class InboundConfiguration {
    @Value("${endpoint.inbound.triggerInboundJob}")
    private String triggerInboundJobEndpoint;
    @Value("${endpoint.integration.triggerInboundTracking}")
    private String triggerInboundTrackingEndpoint;
    @Value("${endpoint.integration.triggerInboundFlow}")
    private String triggerInboundFlowEndpoint;
}
