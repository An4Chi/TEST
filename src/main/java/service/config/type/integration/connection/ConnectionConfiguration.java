package service.config.type.integration.connection;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@NoArgsConstructor
public class ConnectionConfiguration {
    @Value("${endpoint.global.connection}")
    private String globalConnectionTypeEndpoint;
    @Value("${endpoint.global.connectionById}")
    private String globalConnectionTypeByIdEndpoint;
    @Value("${endpoint.local.connection}")
    private String localConnectionTypeEndpoint;
    @Value("${endpoint.local.connectionById}")
    private String localConnectionTypeByIdEndpoint;
    @Value("${endpoint.local.endpoint}")
    private String localEndPointTypeEndpoint;
    @Value("${endpoint.local.endpointById}")
    private String localEndPointTypeByIdEndpoint;
    @Value("${endpoint.global.endpoint}")
    private String globalEndPointTypeEndpoint;
    @Value("${endpoint.global.endpointById}")
    private String globalEndPointTypeByIdEndpoint;
}
