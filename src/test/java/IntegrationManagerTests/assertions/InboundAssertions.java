package IntegrationManagerTests.assertions;

import com.nortecview.config.integration.entity.FlowStatus;
import org.testng.Assert;
import service.inbound.entity.TrackingInboundFlowEntity;

public class InboundAssertions {
    public static void assertSuccessInboundResponse(TrackingInboundFlowEntity trackingResponse, int expectedNumberOfCreatedEntities) {
        Assert.assertEquals(trackingResponse.getStatus(), FlowStatus.SUCCEEDED, String.format("confirm request status is SUCCEEDED. but found %s", trackingResponse.getStatus()));
        Assert.assertNotNull(trackingResponse.getCreatedEntities(), String.format("Created Entities should be %d, but found %s", expectedNumberOfCreatedEntities, trackingResponse.getCreatedEntities()));
        Assert.assertTrue(trackingResponse.getErrors().isEmpty(), String.format("Errors should be null but found: %s", trackingResponse.getErrors()));
    }
}
