package IntegrationManagerTests;

import com.nortecview.config.integration.entity.FieldMappingType;
import com.nortecview.config.integration.entity.MappingDirection;
import com.nortecview.config.integration.entity.SourceEntityType;
import io.locusview.platform.entity.EntityType;
import service.config.type.integration.integration.EntityMappingType;
import service.config.type.integration.integration.FieldMappingEntity;
import service.config.type.integration.integration.IntegrationFlowTypeEntity;
import service.config.type.integration.integration.IntegrationTypeEntity;
import service.integration.entity.IntegrationExecutionFlowEntity;

public class IMIntegrationHelper {
    public static IntegrationTypeEntity createBaseIntegrationTypeEntity(final Long connectionId, final String name) {
        IntegrationTypeEntity integrationTypeEntity = new IntegrationTypeEntity();
        integrationTypeEntity.setId(-1L);
        integrationTypeEntity.setActive(true);
        integrationTypeEntity.setConnectionId(connectionId);
        integrationTypeEntity.setName(name);
        return integrationTypeEntity;
    }

    public static EntityMappingType createEntityMapping(final Long integrationFlowId, final Integer order, final SourceEntityType sourceEntityType, final String targetEntity, final Long sourceEntityTypeId) {
        EntityMappingType entityMappingType = new EntityMappingType();
        entityMappingType.setOrder(order);
        entityMappingType.setIntegrationFlowId(integrationFlowId);
        entityMappingType.setTargetEntity(targetEntity);
        entityMappingType.setSourceEntityType(sourceEntityType);
        entityMappingType.setSourceEntityTypeId(sourceEntityTypeId);
        return entityMappingType;
    }

    public static FieldMappingEntity createBaseFieldMappingBuilder(final Long integrationFlowId,
                                                                   final String sourceFieldKey, final String targetFieldKey,
                                                                   final FieldMappingType fieldMappingType, final int order) {
        FieldMappingEntity fieldMappingEntity = new FieldMappingEntity();
        fieldMappingEntity.setEntityMappingId(-1L);
        fieldMappingEntity.setSourceFieldKey(sourceFieldKey);
        fieldMappingEntity.setTargetFieldKey(targetFieldKey);
        fieldMappingEntity.setFieldMappingType(fieldMappingType);
        fieldMappingEntity.setOrder(order);
        fieldMappingEntity.setIntegrationFlowId(integrationFlowId);
        return fieldMappingEntity;
    }

    public static IntegrationFlowTypeEntity createBaseIntegrationFlowTypeEntity(final String name, final Long endpointId) {
        IntegrationFlowTypeEntity integrationFlowTypeEntity = new IntegrationFlowTypeEntity();
        integrationFlowTypeEntity.setId(-1L);
        integrationFlowTypeEntity.setName(name);
        integrationFlowTypeEntity.setMappingDirection(MappingDirection.OUTBOUND);
        integrationFlowTypeEntity.setEndpointId(endpointId);
        return integrationFlowTypeEntity;
    }

    public static IntegrationExecutionFlowEntity createIntegrationExecutionFlow(final Long integrationFlowId, final EntityType entityType, final String origin, final long entityId, final long workOrderId) {
        IntegrationExecutionFlowEntity integrationExecutionFlowEntity = new IntegrationExecutionFlowEntity();
        integrationExecutionFlowEntity.setIntegrationFlowId(integrationFlowId);
        integrationExecutionFlowEntity.setEntityType(entityType);
        integrationExecutionFlowEntity.setOrigin(origin);
        integrationExecutionFlowEntity.setEntityId(entityId);
        integrationExecutionFlowEntity.setWorkOrderId(workOrderId);
        return integrationExecutionFlowEntity;
    }

}
