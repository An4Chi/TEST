package IntegrationManagerTests.outbound;

import IntegrationManagerTests.IMIntegrationHelper;
import IntegrationManagerTests.IntegrationManagerTestBase;
import com.nortecview.config.integration.entity.FieldMappingType;
import com.nortecview.config.integration.entity.SourceEntityType;
import com.nortecview.config.integration.outbound.entity.IntegrationOutboundFlowResponse;
import com.nortecview.config.integration.outbound.entity.OutboundObject;
import common.exceptions.AutomationInterruptedException;
import io.locusview.platform.docker.DockerContainerFactory;
import io.locusview.platform.entity.EntityType;
import lombok.extern.slf4j.Slf4j;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.testcontainers.containers.MockServerContainer;
import org.testng.annotations.BeforeSuite;
import service.config.type.integration.integration.EntityMappingType;
import service.config.type.integration.integration.FieldMappingEntity;
import service.config.type.integration.integration.IntegrationFlowTypeEntity;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static java.util.Objects.nonNull;
import static org.mockserver.model.HttpRequest.request;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

@Slf4j
public class OutboundTestBase extends IntegrationManagerTestBase {
    protected static final MockServerContainer mockServerContainer = DockerContainerFactory.mockServer();

    @BeforeSuite
    public void setUp() {
        System.out.println("HERE!");
        super.setUp();
        mockServerContainer.start();
    }

    protected OutboundObject extractPostRequestScheduler(final MockServerClient mockServerClient, final String path, final long entityId, final EntityType entityType, final String targetField, final boolean isFieldMapping) {
        HttpRequest[] httpRequests = retrieveRecordedRequests(mockServerClient, path);
        for (HttpRequest req : httpRequests) {
            //using new string because toString return un necessary chars
            OutboundObject outboundFlowResponse = serializeOutboundResult(new String(req.getBody().getRawBytes(), req.getBody().getCharset(StandardCharsets.UTF_8)), entityId, entityType);
            if (nonNull(outboundFlowResponse)) {
                if (isFieldMapping) {
                    if (outboundFlowResponse.getFields().containsKey(targetField)) {
                        return outboundFlowResponse;
                    }
                } else if
                (!isFieldMapping) {
                    return outboundFlowResponse;
                }
            }
        }
        return null;
    }

    private HttpRequest[] retrieveRecordedRequests(final MockServerClient mockServerClient, final String path) {
        Callable<Boolean> outboundResponsecallable = () -> {
            return !Arrays.stream(mockServerClient.retrieveRecordedRequests(request(path))).toList().isEmpty();
        };
        await().atMost(10, TimeUnit.SECONDS)
                .until(outboundResponsecallable);
        return mockServerClient.retrieveRecordedRequests(request(path));
    }

    protected void configRequest(MockServerClient mockServerClient, String path, Integer responseStatusCode) {
        mockServerClient.when(request().withPath(path)).respond(HttpResponse.response().withStatusCode(responseStatusCode));

    }

    protected OutboundObject serializeOutboundResult(String jsonBody, long entityId, EntityType entityType) {
        try {
            String jsonString = jsonBody.replace("\n", "");
            IntegrationOutboundFlowResponse integrationOutboundFlowResponse = objectMapperConfiguration.objectMapper.readValue(jsonString, IntegrationOutboundFlowResponse.class);
            for (OutboundObject outboundFlowResponse : integrationOutboundFlowResponse.getObjects()) {
                if (outboundFlowResponse.getLvId() == entityId && outboundFlowResponse.getEntityType() == entityType) {
                    return outboundFlowResponse;
                }
            }
        } catch (Exception e) {
            log.error("could not serialize outbound request to outbound entity");
            throw new AutomationInterruptedException(e.getMessage());
        }

        return null;
    }

    protected IntegrationFlowTypeEntity createIntegrationFlowTypeEntity(final String integrationFlowName, final Long endpointId) {
        return integrationFacade.createIntegrationFlowTypeEntity(IMIntegrationHelper.createBaseIntegrationFlowTypeEntity(integrationFlowName, endpointId));
    }

    protected EntityMappingType createEntityMappingType(final Long integrationFlowId, final Integer order, final SourceEntityType sourceEntityType, final String targetEntity, final Long sourceEntityTypeId) {
        return integrationFacade.createEntityMappingType(IMIntegrationHelper.createEntityMapping(integrationFlowId, order, sourceEntityType, targetEntity, sourceEntityTypeId));
    }

    protected FieldMappingEntity createFieldMappingType(final Long integrationFlowId,
                                                        final String sourceFieldKey, final String targetFieldKey,
                                                        final FieldMappingType fieldMappingType, final int order) {
        return integrationFacade.createFieldMappingEntity(IMIntegrationHelper.createBaseFieldMappingBuilder(integrationFlowId, sourceFieldKey, targetFieldKey, fieldMappingType, order));
    }
}
