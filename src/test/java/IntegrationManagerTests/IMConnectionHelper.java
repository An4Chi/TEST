package IntegrationManagerTests;

import com.nortecview.config.existing.source.entity.*;
import com.nortecview.config.integration.entity.connection.ConnectionType;
import common.exceptions.AutomationInterruptedException;
import lombok.extern.slf4j.Slf4j;
import service.config.type.integration.connection.*;

import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class IMConnectionHelper {
    public static GlobalConnectionEntity createBaseGlobalConnection(final String name, final ConnectionType connectionType) {
        GlobalConnectionEntity globalConnection = new GlobalConnectionEntity();
        globalConnection.setId(-1L);
        globalConnection.setCreateDate(LocalDateTime.now());
        globalConnection.setName(name);
        globalConnection.setConnectionType(connectionType);
        globalConnection.setLastUpdated(LocalDateTime.now());
        return globalConnection;
    }

    public static LocalConnectionEntity createLocalConnectionEntity(final Long globalConnectionId, final String domain, final AuthenticationParams authenticationParams) {
        LocalConnectionEntity localConnectionEntity = new LocalConnectionEntity();
        localConnectionEntity.setConnectionId(globalConnectionId);
        localConnectionEntity.setActive(true);
        localConnectionEntity.setDomain(domain);
        localConnectionEntity.setAuthenticationParams(authenticationParams);
        return localConnectionEntity;
    }

    public static GlobalEndPointType createGlobalEndPointType(final Long globalConnectionId, final String alias, Integer order) {
        GlobalEndPointType globalEndPointType = new GlobalEndPointType();
        globalEndPointType.setConnectionId(globalConnectionId);
        globalEndPointType.setId(-1L);
        globalEndPointType.setAlias(alias);
        globalEndPointType.setOrder(order);
        return globalEndPointType;
    }

    public static LocalEndPointTypeEntity createLocalEndPointTypeEntity(final Long globalConnectionId, final Long localConnectionId, final String path, final Long globalEndPointId) {
        LocalEndPointTypeEntity localEndPointType = new LocalEndPointTypeEntity();
        localEndPointType.setConnectionId(globalConnectionId);
        localEndPointType.setId(-1L);
        localEndPointType.setPath(path);
        localEndPointType.setLocalConnectionId(localConnectionId);
        localEndPointType.setEndpointId(globalEndPointId);
        return localEndPointType;
    }

    public static ConnectionTypeEntity createConnectionType(final Map<LocalEndPointTypeEntity, GlobalEndPointType> endpoints, final LocalConnectionEntity localConnectionEntity, final GlobalConnectionEntity globalConnectionEntity) {
        List<FinalEndPointEntity> finalEndPointList = endpoints.entrySet().stream()
                .map(entry -> new FinalEndPointEntity(entry.getValue(), entry.getKey()))
                .collect(Collectors.toList());
        return new ConnectionTypeEntity(globalConnectionEntity, localConnectionEntity, finalEndPointList);
    }

    public static AuthenticationParams buildAuthenticationParams(final ConnectionType connectionType, final @Nullable String domainToken, final @Nullable String userName, final @Nullable String password, final @Nullable String port) {

        switch (connectionType) {
            case ESRI:
                return EsriTokenAuthenticationParams.Builder.anEsriTokenAuthenticationParams()
                        .withAuthenticationType(AuthenticationType.ESRI_TOKEN)
                        .withToken(domainToken)
                        .build();
            case HTTPS:
                return TokenAuthenticationParams.Builder.aTokenAuthenticationParams()
                        .withAuthenticationType(AuthenticationType.TOKEN)
                        .withToken(domainToken)
                        .build();
            case FTP:
                return FtpAuthenticationParams.Builder.anFtpAuthenticationParams().withAuthenticationType(AuthenticationType.FTP).withPassword(password).withUsername(userName).withPort(port).build();

            default:
                log.error("authentication type not supported");
                throw new AutomationInterruptedException("Cannot generate authentication params for type: " + connectionType);
        }
    }
}
