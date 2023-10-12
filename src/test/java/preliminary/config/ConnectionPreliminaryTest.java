package preliminary.config;

import com.nortecview.config.integration.entity.connection.GlobalConnection;
import com.nortecview.config.integration.entity.connection.GlobalEndpoint;
import com.nortecview.config.integration.entity.connection.LocalConnection;
import com.nortecview.config.integration.entity.connection.LocalEndpoint;
import service.config.type.integration.connection.GlobalConnectionEntity;
import service.config.type.integration.connection.GlobalEndPointType;
import service.config.type.integration.connection.LocalConnectionEntity;
import service.config.type.integration.connection.LocalEndPointTypeEntity;
import org.testng.annotations.Test;
import org.springframework.boot.test.context.TestComponent;

import static preliminary.FieldsComparison.comparingFields;

@TestComponent
public class ConnectionPreliminaryTest {
    private final Class<GlobalConnectionEntity> globalConnectionTypeEntity = GlobalConnectionEntity.class;
    private final Class<GlobalConnection> platformGlobalConnectionType = GlobalConnection.class;
    private final Class<LocalConnectionEntity> localConnectionTypeEntity = LocalConnectionEntity.class;
    private final Class<LocalConnection> platformLocalConnectionType = LocalConnection.class;
    private final Class<GlobalEndPointType> globalEndPointTypeEntity = GlobalEndPointType.class;
    private final Class<GlobalEndpoint> platformGlobalEndPointType = GlobalEndpoint.class;
    private final Class<LocalEndPointTypeEntity> localEndpointTypeEntity = LocalEndPointTypeEntity.class;
    private final Class<LocalEndpoint> platformLocalEndPointType = LocalEndpoint.class;

    @Test
    void prerequisiteValidation_integrationType() {
        comparingFields(globalConnectionTypeEntity.getClass(), platformGlobalConnectionType.getClass());
    }

    @Test
    void prerequisiteValidation_integrationFlowType() {
        comparingFields(localConnectionTypeEntity.getClass(), platformLocalConnectionType.getClass());
    }

    @Test
    void prerequisiteValidation_entityMapping() {
        comparingFields(localEndpointTypeEntity.getClass(), platformLocalEndPointType.getClass());
    }

    @Test
    void prerequisiteValidation_fieldMapping() {
        comparingFields(globalEndPointTypeEntity.getClass(), platformGlobalEndPointType.getClass());
    }
}
