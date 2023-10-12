package service.config.type.integration.integration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nortecview.config.conditiontype.entity.ConditionType;
import com.nortecview.config.integration.entity.SourceEntityType;
import com.nortecview.config.integration.field.FieldMapping;
import io.locusview.platform.common.LocalDateTimeDeserializer;
import io.locusview.platform.common.LocalDateTimeSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EntityMappingType {
    private Long id;
    private Long integrationFlowId;
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
    private String description;
    private SourceEntityType sourceEntityType;
    private Long sourceEntityTypeId;
    private Long endpointId;
    private String targetEntity;
    private ConditionType conditionType;
    private List<FieldMapping> fieldsMapping;
    private int order;
    @JsonIgnore
    private boolean deleted;
}
