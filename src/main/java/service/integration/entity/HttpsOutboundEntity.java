package service.integration.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nortecview.config.integration.outbound.entity.*;
import com.nortecview.field.geometry.entity.Geometry;
import com.nortecview.field.project.entity.ProjectStatus;
import io.locusview.platform.entity.EntityType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HttpsOutboundEntity {
    protected long lvId;
    protected String referenceId;
    protected String typeName;
    protected String targetEntity;
    protected EntityType entityType;
    protected ProjectStatus status;
    protected BigDecimal lengthInFeet;
    protected OutboundObjectParent parent;
    protected Map<String, Object> fields;
    protected List<Geometry> geometries;
    protected AssignmentPreview assignment;
    protected PermissionPreview permission;
    private Set<OutboundReconciliationAnalytics> reconAnalytics;
}
