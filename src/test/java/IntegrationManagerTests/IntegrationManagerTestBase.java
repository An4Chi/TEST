package IntegrationManagerTests;

import com.nortecview.config.integration.entity.FlowStatus;
import com.nortecview.config.integration.entity.connection.ConnectionType;
import service.auth.AuthServiceConfiguration;
import service.auth.AuthServiceEntity;
import service.auth.AuthServiceFacade;
import common.BaseConfiguration;
import common.BaseFacadeEntity;
import common.BaseFacade;
import common.ObjectMapperConfiguration;
import service.config.ConfigServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import service.config.type.integration.connection.*;
import service.config.type.integration.integration.IntegrationFacade;
import service.config.type.integration.integration.IntegrationTypeEntity;
import service.inbound.facade.InboundFacade;
import service.inbound.facade.InboundServiceConfiguration;
import service.integration.facade.IntegrationServiceFacade;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

@ContextConfiguration(classes = {BaseConfiguration.class, AuthServiceConfiguration.class, ConfigServiceConfiguration.class, InboundServiceConfiguration.class})
@TestComponent
@ComponentScan(basePackages = {"common", "service.auth", "service.config", "service.inbound"})
public class IntegrationManagerTestBase extends AbstractTestNGSpringContextTests {

    @Autowired
    protected ObjectMapperConfiguration objectMapperConfiguration;
    @Autowired
    private BaseFacadeEntity baseFacadeEntity;
    @Autowired
    private BaseFacade baseFacade;
    @Autowired
    private AuthServiceEntity authConfiguration;
    @Autowired
    private AuthServiceFacade authServiceFacade;
    @Autowired
    protected InboundFacade inboundFacade;
    @Autowired
    protected ConnectionFacade connectionFacade;
    @Autowired
    protected IntegrationFacade integrationFacade;
    String token = "==asdadasdasdsadasdasdasdasdasdaasdfasfasfasfafasfsa";

    public void setUp() {
        System.out.println("HERE!");
        String token = this.token;
        baseFacade.setTokenHeader(token);
        System.out.println(token);
    }

    protected void waitUntilInboundFlowDone(final FlowStatus flowStatus, final Long trackingId) {
        Callable<Boolean> statusResponsecallable = () -> {
            inboundFacade.triggerInboundJob();
            inboundFacade.triggerInboundJob();
            return inboundFacade.getInboundTrackingStatus(trackingId).getStatus().equals(flowStatus);
        };
        await().atMost(30, TimeUnit.SECONDS).until(statusResponsecallable);
    }

    protected ConnectionTypeEntity createConnectionType(final List<String> endpointsAlias, final List<String> endpointsPath) {
        GlobalConnectionEntity globalConnectionEntity = connectionFacade.createGlobalConnectionType(IMConnectionHelper.createBaseGlobalConnection("https automation outbound connection", ConnectionType.HTTPS));
        LocalConnectionEntity localConnectionEntity = connectionFacade.createLocalConnectionType(IMConnectionHelper.createLocalConnectionEntity(globalConnectionEntity.getId(), "f4fb5cc5-b1f2-4ec7-a5a9-b2caad1ff71b.mock.pstmn.io", IMConnectionHelper.buildAuthenticationParams(ConnectionType.HTTPS, "f4fb5cc5-b1f2-4ec7-a5a9-b2caad1ff71b.mock.pstmn.io", null, null, null)));
        Map<LocalEndPointTypeEntity, GlobalEndPointType> endpoints = this.createConnectionEndPoints(globalConnectionEntity, localConnectionEntity, endpointsAlias, endpointsPath);
        return IMConnectionHelper.createConnectionType(endpoints, localConnectionEntity, globalConnectionEntity);
    }

    private Map<LocalEndPointTypeEntity, GlobalEndPointType> createConnectionEndPoints(final GlobalConnectionEntity globalConnectionEntity, final LocalConnectionEntity localConnectionEntity, List<String> endpointsAlias, List<String> endpointsPath) {
        int order = 1, pathIndex = 0;
        Map<LocalEndPointTypeEntity, GlobalEndPointType> endpoints = new HashMap<>();
        for (String endpointAlias : endpointsAlias) {
            GlobalEndPointType globalEndPointType = connectionFacade.createGlobalEndPointType(IMConnectionHelper.createGlobalEndPointType(globalConnectionEntity.getId(), endpointAlias, order));
            LocalEndPointTypeEntity localEndPointTypeEntity = (IMConnectionHelper.createLocalEndPointTypeEntity(globalConnectionEntity.getId(), localConnectionEntity.getId(), endpointsPath.get(pathIndex), globalEndPointType.getId()));
            endpoints.put(localEndPointTypeEntity, globalEndPointType);
            order++;
            pathIndex++;
        }
        return endpoints;
    }

    protected IntegrationTypeEntity createIntegrationTypeEntity(final Long connectionId, final String integrationName) {
        return integrationFacade.createIntegrationTypeEntity(IMIntegrationHelper.createBaseIntegrationTypeEntity(connectionId, integrationName));
    }
}
