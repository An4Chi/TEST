package service.integration.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.locusview.platform.entity.EntityType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IntegrationExecutionFlowEntity {
    private Long workOrderId;
    private Long integrationFlowId;
    private EntityType entityType;
    private Long entityId;
    private String origin;
    private Long triggerId;
}
