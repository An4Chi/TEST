package service.config.type.integration.integration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@NoArgsConstructor
public class IntegrationConfiguration {
    @Value("${endpoint.mapping.entity}")
    private String entityMappingEndpoint;
    @Value("${endpoint.mapping.entityById}")
    private String entityMappingByIdEndpoint;
    @Value("${endpoint.mapping.field}")
    private String fieldMappingEndpoint;
    @Value("${endpoint.mapping.fieldById}")
    private String fieldMappingByIdEndpoint;
    @Value("${endpoint.integration.flow}")
    private String integrationFlowTypeEndpoint;
    @Value("${endpoint.integration.flowById}")
    private String integrationFlowByIdEndpoint;
    @Value("${endpoint.integration.type}")
    private String integrationTypeEndpoint;
    @Value("${endpoint.integration.typeById}")
    private String integrationTypeByIdEndpoint;
}
