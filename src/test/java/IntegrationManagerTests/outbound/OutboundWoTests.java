package IntegrationManagerTests.outbound;

import IntegrationManagerTests.IMConnectionHelper;
import com.nortecview.config.integration.entity.connection.ConnectionType;
import org.junit.Assert;
import org.mockserver.client.MockServerClient;
import org.testng.annotations.BeforeClass;
import service.config.type.integration.connection.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OutboundWoTests extends OutboundTestBase {
    protected static MockServerClient mockServerClient;

    @BeforeClass
    public void beforeClass() {
        GlobalConnectionEntity globalConnectionEntity = connectionFacade.createGlobalConnectionType(IMConnectionHelper.createBaseGlobalConnection("https automation outbound connection", ConnectionType.HTTPS));
        LocalConnectionEntity localConnectionEntity = connectionFacade.createLocalConnectionType(IMConnectionHelper.createLocalConnectionEntity(globalConnectionEntity.getId(), "f4fb5cc5-b1f2-4ec7-a5a9-b2caad1ff71b.mock.pstmn.io", IMConnectionHelper.buildAuthenticationParams(ConnectionType.HTTPS, "f4fb5cc5-b1f2-4ec7-a5a9-b2caad1ff71b.mock.pstmn.io", null, null, null)));
        GlobalEndPointType globalEndPointType = connectionFacade.createGlobalEndPointType(IMConnectionHelper.createGlobalEndPointType(globalConnectionEntity.getId(), "automationEndPoint", 1));
        LocalEndPointTypeEntity localEndPointTypeEntity = (IMConnectionHelper.createLocalEndPointTypeEntity(globalConnectionEntity.getId(), localConnectionEntity.getId(), "outbound", globalEndPointType.getId()));
        Map<LocalEndPointTypeEntity, GlobalEndPointType> endpoints = Map.of(localEndPointTypeEntity, globalEndPointType);
        ConnectionTypeEntity connectionType = IMConnectionHelper.createConnectionType(endpoints, localConnectionEntity, globalConnectionEntity);
        initializeMockServer();

    }

    public void initializeMockServer() {
        mockServerClient = new MockServerClient(System.getenv("JENKINS_HOST"), mockServerContainer.getServerPort());
        Assert.assertTrue(mockServerClient.hasStarted(2, 10, TimeUnit.SECONDS));
    }
}
