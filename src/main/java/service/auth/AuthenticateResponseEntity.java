package service.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class AuthenticateResponseEntity {
    private String token;
    private Object user;
    private Object session;

    public AuthenticateResponseEntity(String token, Object user, Object session) {
        this.token = token;
        this.user = user;
        this.session = session;
    }
}
