package service.config.type.integration.connection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class GlobalEndPointType extends BaseEndPointTypeEntity {
    private String alias;
    private Integer order;
    @JsonIgnore
    private Boolean deleted;
}
