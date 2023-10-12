package preliminary.config;

import com.nortecview.config.integration.entity.EntityMapping;
import com.nortecview.config.integration.entity.FieldMappingType;
import com.nortecview.config.integration.entity.IntegrationFlow;
import com.nortecview.config.integration.entity.IntegrationType;
import service.config.type.integration.integration.EntityMappingType;
import service.config.type.integration.integration.FieldMappingEntity;
import service.config.type.integration.integration.IntegrationFlowTypeEntity;
import service.config.type.integration.integration.IntegrationTypeEntity;
import org.springframework.boot.test.context.TestComponent;
import org.testng.annotations.Test;

import static preliminary.FieldsComparison.comparingFields;

@TestComponent
public class IntegrationPreliminaryTest {
    private final Class<IntegrationTypeEntity> integrationTypeEntity = IntegrationTypeEntity.class;
    private final Class<IntegrationType> platformIntegrationType = IntegrationType.class;
    private final Class<IntegrationFlowTypeEntity> integrationFlowTypeEntity = IntegrationFlowTypeEntity.class;
    private final Class<IntegrationFlow> platformIntegrationFlowType = IntegrationFlow.class;
    private final Class<EntityMappingType> entityMappingTypeEntity = EntityMappingType.class;
    private final Class<EntityMapping> platformEntityMappingType = EntityMapping.class;
    private final Class<FieldMappingEntity> fieldMappingTypeEntity = FieldMappingEntity.class;
    private final Class<FieldMappingType> platformFieldMappingType = FieldMappingType.class;

    @Test
    void prerequisiteValidation_integrationType() {
        comparingFields(integrationTypeEntity.getClass(), platformIntegrationType.getClass());
    }

    @Test
    void prerequisiteValidation_integrationFlowType() {
        comparingFields(integrationFlowTypeEntity.getClass(), platformIntegrationFlowType.getClass());
    }

    @Test
    void prerequisiteValidation_entityMapping() {
        comparingFields(entityMappingTypeEntity.getClass(), platformEntityMappingType.getClass());
    }

    @Test
    void prerequisiteValidation_fieldMapping() {
        comparingFields(fieldMappingTypeEntity.getClass(), platformFieldMappingType.getClass());
    }
}
