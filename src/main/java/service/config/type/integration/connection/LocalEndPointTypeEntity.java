package service.config.type.integration.connection;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class LocalEndPointTypeEntity extends BaseEndPointTypeEntity {
    private Long localConnectionId;
    private Long endpointId;
    private String path;
    private Boolean deleted;

}
