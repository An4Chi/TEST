package service.integration.facade;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@NoArgsConstructor
public class IntegrationManagerConfiguration {
    @Value("${endpoint.integration.sendMessages}")
    private String sendMessagesEndpoint;
    @Value("${endpoint.integration.resetFailures}")
    private String resetFailuresEndpoint;
    @Value("${endpoint.integration.outboundProcess}")
    private String outboundProcessEndpoint;
    @Value("${endpoint.integration.triggerIntegrationFlow}")
    private String triggerIntegrationFlowEndpoint;

}
