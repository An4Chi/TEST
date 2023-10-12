package service.config.type.integration.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import common.BaseFacade;
import common.ObjectMapperConfiguration;
import common.exceptions.AutomationInterruptedException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class IntegrationFacade {
    @Resource
    private final BaseFacade baseFacade;
    @Resource
    private final ObjectMapperConfiguration objectMapperConfiguration;
    @Resource
    private final IntegrationConfiguration integrationConfiguration;

    @Autowired
    public IntegrationFacade(BaseFacade baseFacade, ObjectMapperConfiguration objectMapperConfiguration, IntegrationConfiguration integrationConfiguration) {
        this.baseFacade = baseFacade;
        this.objectMapperConfiguration = objectMapperConfiguration;
        this.integrationConfiguration = integrationConfiguration;
    }

    public IntegrationTypeEntity createIntegrationTypeEntity(IntegrationTypeEntity integrationTypeEntity) {
        try {
            final String body = objectMapperConfiguration.getObjectMapper().writeValueAsString(integrationTypeEntity);
            log.debug("integration type created successfully");
            return baseFacade.post(integrationConfiguration.getIntegrationTypeEndpoint(), body, IntegrationTypeEntity.class);

        } catch (JsonProcessingException e) {
            log.error("could not deserialize create integration request entity into json");
            throw new AutomationInterruptedException(e.getMessage());
        }
    }

    public IntegrationFlowTypeEntity createIntegrationFlowTypeEntity(IntegrationFlowTypeEntity integrationFlowTypeEntity) {
        try {
            final String body = objectMapperConfiguration.getObjectMapper().writeValueAsString(integrationFlowTypeEntity);
            log.debug("integration flow type created successfully");
            return baseFacade.post(integrationConfiguration.getIntegrationFlowTypeEndpoint(), body, IntegrationFlowTypeEntity.class);

        } catch (JsonProcessingException e) {
            log.error("could not deserialize create integration flow request entity into json");
            throw new AutomationInterruptedException(e.getMessage());
        }
    }

    public EntityMappingType createEntityMappingType(EntityMappingType entityMappingType) {
        try {
            final String body = objectMapperConfiguration.getObjectMapper().writeValueAsString(entityMappingType);
            log.debug("entity mapping type created successfully");
            return baseFacade.post(integrationConfiguration.getEntityMappingEndpoint(), body, EntityMappingType.class);

        } catch (JsonProcessingException e) {
            log.error("could not deserialize create entity mapping request entity into json");
            throw new AutomationInterruptedException(e.getMessage());
        }
    }

    public FieldMappingEntity createFieldMappingEntity(FieldMappingEntity fieldMappingEntity) {
        try {
            final String body = objectMapperConfiguration.getObjectMapper().writeValueAsString(fieldMappingEntity);
            log.debug("field mapping type created successfully");
            return baseFacade.post(integrationConfiguration.getFieldMappingEndpoint(), body, FieldMappingEntity.class);

        } catch (JsonProcessingException e) {
            log.error("could not deserialize create field mapping request entity into json");
            throw new AutomationInterruptedException(e.getMessage());
        }
    }
}
