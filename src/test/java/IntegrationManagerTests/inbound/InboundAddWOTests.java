package IntegrationManagerTests.inbound;

import IntegrationManagerTests.IntegrationManagerTestBase;
import com.nortecview.config.integration.entity.FlowStatus;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.annotation.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import service.inbound.entity.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static IntegrationManagerTests.assertions.InboundAssertions.assertSuccessInboundResponse;
import static IntegrationManagerTests.inbound.InboundTestsHandler.*;

@TestComponent
public class InboundAddWOTests extends IntegrationManagerTestBase {
    private static final String PROJECT_TYPE_NAME = "ALL_INCLUDED";
    private static final String LAYOUT_NAME = "Project Name";
    private static final String ENDPOINT_ALIAS_NAME = "inbound";
    private static final String INTEGRATION_NAME = "integration for inbound happy flow";

    @BeforeClass
    void prerequisites() {
        super.setUp();
    }

    @Test
    @Description("LV-30363 - Add work order via inbound - sanity")
    void AddWOViaInbound() {
        UUID referencedId = UUID.randomUUID();
        InboundEntity inboundEntity = createBaseInboundObject(referencedId, InboundAction.ADD, InboundEntityType.WORK_ORDER, PROJECT_TYPE_NAME, ProjectStatus.NEW, Map.of(LAYOUT_NAME, referencedId));
        InboundRequestEntity inboundRequestEntity = createBaseInboundRequest(List.of(inboundEntity), INTEGRATION_NAME, ENDPOINT_ALIAS_NAME);
        Long trackingId = inboundFacade.triggerInboundFlow(inboundRequestEntity).getInboundFlowId();
        waitUntilInboundFlowDone(FlowStatus.SUCCEEDED, trackingId);
        TrackingInboundFlowEntity trackingInboundFlowEntity = inboundFacade.getInboundTrackingStatus(trackingId);
        assertSuccessInboundResponse(trackingInboundFlowEntity, 1);
    }

}
