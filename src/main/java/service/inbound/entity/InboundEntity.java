package service.inbound.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nortecview.field.assignment.entity.Assignment;
import com.nortecview.field.permission.entity.Permission;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InboundEntity {
    protected InboundAction action;
    protected String referenceId;
    protected String lvId;
    protected String typeName;
    protected ProjectStatus status;
    private Assignment assignment;
    private Permission permission;
    protected InboundEntityType entityType;
    protected InboundParentEntity parent;
    protected Map<String, Object> fields;
    protected List<InboundGeometryEntity> geometries;
    protected List<InboundMaterialEntity> materials;
}
