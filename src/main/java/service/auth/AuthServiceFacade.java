package service.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import common.exceptions.AutomationInterruptedException;
import common.BaseFacade;
import common.ObjectMapperConfiguration;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthServiceFacade {

    @Resource
    private final BaseFacade baseFacade;
    private final String authenticateEndPoint;
    @Resource
    private final AuthServiceEntity authConfiguration;
    @Resource
    private final ObjectMapperConfiguration objectMapperConfiguration;


    @Autowired
    public AuthServiceFacade(BaseFacade baseFacade, AuthServiceEntity authConfiguration, ObjectMapperConfiguration objectMapperConfiguration) {
        System.out.println("HERE!");
        this.baseFacade = baseFacade;
        this.authConfiguration = authConfiguration;
        this.objectMapperConfiguration = objectMapperConfiguration;
        this.authenticateEndPoint = authConfiguration.getAuthenticateEndPoint();

    }


    public AuthenticateResponseEntity authenticate() {
        AuthenticateRequestEntity authenticateRequestEntity = new AuthenticateRequestEntity(this.authConfiguration.getUserName(), this.authConfiguration.getPassword());

        try {
            final String body = objectMapperConfiguration.getObjectMapper().writeValueAsString(authenticateRequestEntity);
            log.debug("Authenticate successfully");
            return baseFacade.post(authenticateEndPoint, body, AuthenticateResponseEntity.class);
        } catch (JsonProcessingException exception) {
            log.error("could not deserialize authentication request entity into json");
            throw new AutomationInterruptedException(exception.getMessage());
        }

    }
}
