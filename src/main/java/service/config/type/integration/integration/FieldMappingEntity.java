package service.config.type.integration.integration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nortecview.config.conditiontype.entity.ConditionType;
import com.nortecview.config.integration.entity.FieldMappingType;
import com.nortecview.config.integration.entity.FieldSource;
import com.nortecview.config.integration.entity.FieldTransformationType;
import io.locusview.platform.common.LocalDateTimeDeserializer;
import io.locusview.platform.common.LocalDateTimeSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FieldMappingEntity {
    private Long id;
    private Long entityMappingId;
    private Long integrationFlowId;
    private FieldMappingType fieldMappingType;
    private FieldSource fieldSource;
    private Long fieldSourceTypeId;
    private Long sourceFieldId;
    private String sourceFieldKey;
    private String targetFieldKey;
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
    private int order;
    private FieldTransformationType transformationType;
    private Long transformationTypeId;
    private String transformationContext;
    @JsonIgnore
    private boolean deleted;
    private ConditionType conditionType;
}
