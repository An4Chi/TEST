package service.config.type.integration.connection;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ConnectionTypeEntity {
    private GlobalConnectionEntity globalConnectionEntity;
    private LocalConnectionEntity localConnectionEntity;
    private List<FinalEndPointEntity> finalEndPointEntityList;

    public ConnectionTypeEntity(GlobalConnectionEntity globalConnectionEntity, LocalConnectionEntity localConnectionEntity, List<FinalEndPointEntity> finalEndPointEntityList) {
        this.globalConnectionEntity = globalConnectionEntity;
        this.localConnectionEntity = localConnectionEntity;
        this.finalEndPointEntityList = finalEndPointEntityList;
    }

}
