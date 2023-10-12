package service.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("classpath:config.properties")
@Component
@Getter
@NoArgsConstructor
public class AuthServiceEntity {
    @Value("${userName}")
    private String userName;

    @Value("${password}")
    private String password;

    @Value("${endpoint.auth.authentication}")
    private String authenticateEndPoint;
}
