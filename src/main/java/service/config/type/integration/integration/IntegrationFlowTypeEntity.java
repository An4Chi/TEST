package service.config.type.integration.integration;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nortecview.config.integration.entity.ChangeStatusTo;
import com.nortecview.config.integration.entity.EntityMapping;
import com.nortecview.config.integration.entity.ExportType;
import com.nortecview.config.integration.entity.MappingDirection;
import io.locusview.platform.common.LocalDateTimeDeserializer;
import io.locusview.platform.common.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.List;

public class IntegrationFlowTypeEntity {
    private Long id;
    private Long integrationTypeId;
    private Long endpointId;
    private String name;
    private String description;
    private MappingDirection mappingDirection;
    private Boolean deleted;
    private Boolean workOrderGrouping;
    private Boolean delta = false;
    private ChangeStatusTo changeStatusTo;
    private ExportType exportType;
    private List<EntityMapping> entityMappings;
    private ObjectNode gdbSchemaFile;
    @JsonSerialize(
            using = LocalDateTimeSerializer.class
    )
    @JsonDeserialize(
            using = LocalDateTimeDeserializer.class
    )
    private LocalDateTime createDate;
    @JsonSerialize(
            using = LocalDateTimeSerializer.class
    )
    @JsonDeserialize(
            using = LocalDateTimeDeserializer.class
    )
    private LocalDateTime lastUpdated;


    public IntegrationFlowTypeEntity(Long id, Long integrationTypeId, Long endpointId, String name, String description, MappingDirection mappingDirection, boolean deleted, boolean workOrderGrouping, ChangeStatusTo changeStatusTo, ExportType exportType, List<EntityMapping> entityMappings, ObjectNode gdbSchemaFile, LocalDateTime createDate, LocalDateTime lastUpdated) {
        this.id = id;
        this.integrationTypeId = integrationTypeId;
        this.endpointId = endpointId;
        this.name = name;
        this.description = description;
        this.mappingDirection = mappingDirection;
        this.deleted = deleted;
        this.workOrderGrouping = workOrderGrouping;
        this.changeStatusTo = changeStatusTo;
        this.exportType = exportType;
        this.entityMappings = entityMappings;
        this.gdbSchemaFile = gdbSchemaFile;
        this.createDate = createDate;
        this.lastUpdated = lastUpdated;
    }

    public IntegrationFlowTypeEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIntegrationTypeId() {
        return integrationTypeId;
    }

    public void setIntegrationTypeId(Long integrationTypeId) {
        this.integrationTypeId = integrationTypeId;
    }

    public Long getEndpointId() {
        return endpointId;
    }

    public void setEndpointId(Long endpointId) {
        this.endpointId = endpointId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MappingDirection getMappingDirection() {
        return mappingDirection;
    }

    public void setMappingDirection(MappingDirection mappingDirection) {
        this.mappingDirection = mappingDirection;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean isWorkOrderGrouping() {
        return workOrderGrouping;
    }

    public void setWorkOrderGrouping(boolean workOrderGrouping) {
        this.workOrderGrouping = workOrderGrouping;
    }

    public Boolean isDelta() {
        return delta;
    }

    public void setDelta(boolean delta) {
        this.delta = delta;
    }

    public ChangeStatusTo getChangeStatusTo() {
        return changeStatusTo;
    }

    public void setChangeStatusTo(ChangeStatusTo changeStatusTo) {
        this.changeStatusTo = changeStatusTo;
    }

    public ExportType getExportType() {
        return exportType;
    }

    public void setExportType(ExportType exportType) {
        this.exportType = exportType;
    }

    public List<EntityMapping> getEntityMappings() {
        return entityMappings;
    }

    public void setEntityMappings(List<EntityMapping> entityMappings) {
        this.entityMappings = entityMappings;
    }

    public ObjectNode getGdbSchemaFile() {
        return gdbSchemaFile;
    }

    public void setGdbSchemaFile(ObjectNode gdbSchemaFile) {
        this.gdbSchemaFile = gdbSchemaFile;
    }
}
