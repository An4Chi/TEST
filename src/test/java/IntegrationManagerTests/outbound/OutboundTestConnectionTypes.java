package IntegrationManagerTests.outbound;

import IntegrationManagerTests.IMConnectionHelper;
import com.nortecview.config.existing.source.entity.AuthenticationParams;
import com.nortecview.config.existing.source.entity.AuthenticationType;
import com.nortecview.config.integration.entity.connection.ConnectionType;
import com.nortecview.config.integration.entity.connection.GlobalEndpoint;
import org.junit.Assert;
import org.mockserver.client.MockServerClient;
import org.springframework.context.annotation.Description;
import org.testng.annotations.Test;
import service.config.type.integration.connection.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OutboundTestConnectionTypes extends OutboundTestBase {

    protected static MockServerClient mockServerClient;

    @Test
    @Description("Testing All Types Of Authentication")
    public void connectionTest() {
        GlobalConnectionEntity globalConnectionEntity = connectionFacade.createGlobalConnectionType(IMConnectionHelper.createBaseGlobalConnection("FTP automation outbound connection", ConnectionType.FTP));
        LocalConnectionEntity localConnectionEntity = connectionFacade.createLocalConnectionType(IMConnectionHelper.createLocalConnectionEntity(globalConnectionEntity.getId(), "FTP_ADDRESS_HERE", IMConnectionHelper.buildAuthenticationParams(ConnectionType.FTP, "www.FTPADRESSHERE.com/", "Vitalii", "12345677Aqa", "22")));
        GlobalEndPointType globalEndPointType = connectionFacade.createGlobalEndPointType(IMConnectionHelper.createGlobalEndPointType(globalConnectionEntity.getId(), "AutomationEndPoint", 1));
        LocalEndPointTypeEntity localEndPointTypeEntity = connectionFacade.createLocalEndPointType(IMConnectionHelper.createLocalEndPointTypeEntity(globalConnectionEntity.getId(), localConnectionEntity.getId(), "LocalEndPoint", globalEndPointType.getId()));
        Map<LocalEndPointTypeEntity, GlobalEndPointType> endpoints = Map.of(localEndPointTypeEntity, globalEndPointType);
        ConnectionTypeEntity connectionType = IMConnectionHelper.createConnectionType(endpoints,localConnectionEntity,globalConnectionEntity);
    }

    public void initializeMockServer() {
        mockServerClient = new MockServerClient(System.getenv("JENKINS_HOST"), mockServerContainer.getServerPort());
        Assert.assertTrue(mockServerClient.hasStarted(2, 10, TimeUnit.SECONDS));
    }
}


