package service.config.type.integration.connection;

import com.fasterxml.jackson.core.JsonProcessingException;
import common.BaseFacade;
import common.ObjectMapperConfiguration;
import common.exceptions.AutomationInterruptedException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConnectionFacade {
    @Resource
    private final BaseFacade baseFacade;
    @Resource
    private final ObjectMapperConfiguration objectMapperConfiguration;
    @Resource
    private final ConnectionConfiguration connectionConfiguration;

    public ConnectionFacade(BaseFacade baseFacade, ObjectMapperConfiguration objectMapperConfiguration, ConnectionConfiguration connectionConfiguration) {
        this.baseFacade = baseFacade;
        this.objectMapperConfiguration = objectMapperConfiguration;
        this.connectionConfiguration = connectionConfiguration;
    }

    public GlobalConnectionEntity createGlobalConnectionType(GlobalConnectionEntity globalConnectionEntity) {
        try {
            final String body = objectMapperConfiguration.getObjectMapper().writeValueAsString(globalConnectionEntity);
            log.debug("global connection type created successfully");
            return baseFacade.post(connectionConfiguration.getGlobalConnectionTypeEndpoint(), body, GlobalConnectionEntity.class);

        } catch (JsonProcessingException e) {
            log.error("could not deserialize create global connection request entity into json");
            throw new AutomationInterruptedException(e.getMessage());
        }
    }

    public LocalConnectionEntity createLocalConnectionType(LocalConnectionEntity localConnectionEntity) {
        try {
            final String body = objectMapperConfiguration.getObjectMapper().writeValueAsString(localConnectionEntity);
            log.debug("local connection type created successfully");
            return baseFacade.post(connectionConfiguration.getLocalConnectionTypeEndpoint(), body, LocalConnectionEntity.class);

        } catch (JsonProcessingException e) {
            log.error("could not deserialize create local connection request entity into json");
            throw new AutomationInterruptedException(e.getMessage());
        }
    }

    public LocalEndPointTypeEntity createLocalEndPointType(LocalEndPointTypeEntity localEndPointTypeEntity) {
        try {
            final String body = objectMapperConfiguration.getObjectMapper().writeValueAsString(localEndPointTypeEntity);
            log.debug("local EndPoint type created successfully");
            return baseFacade.post(connectionConfiguration.getLocalEndPointTypeEndpoint(), body, LocalEndPointTypeEntity.class);

        } catch (JsonProcessingException e) {
            log.error("could not deserialize create local EndPoint request entity into json");
            throw new AutomationInterruptedException(e.getMessage());
        }
    }

    public GlobalEndPointType createGlobalEndPointType(GlobalEndPointType globalEndPointType) {
        try {
            final String body = objectMapperConfiguration.getObjectMapper().writeValueAsString(globalEndPointType);
            log.debug("global EndPoint type created successfully");
            return baseFacade.post(connectionConfiguration.getGlobalEndPointTypeEndpoint(), body, GlobalEndPointType.class);

        } catch (JsonProcessingException e) {
            log.error("could not deserialize create global EndPoint request entity into json");
            throw new AutomationInterruptedException(e.getMessage());
        }
    }
}

