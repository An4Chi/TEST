package service.inbound.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nortecview.config.integration.entity.FlowStatus;
import com.nortecview.config.integration.inbound.entity.ObjectError;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrackingInboundFlowEntity {
    private String context;
    private Long inboundId;
    private FlowStatus status;
    private Map<String, Long> createdEntities;
    private Map<String, Long> updatedEntities;
    private Map<String, List<ObjectError>> errors;

}
