package service.config.type.integration.connection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FinalEndPointEntity {
    private GlobalEndPointType globalEndPointType;
    private LocalEndPointTypeEntity localEndPointTypeEntity;

    public FinalEndPointEntity(GlobalEndPointType globalEndPointType, LocalEndPointTypeEntity localEndPointTypeEntity) {
        this.globalEndPointType = globalEndPointType;
        this.localEndPointTypeEntity = localEndPointTypeEntity;
    }

}
