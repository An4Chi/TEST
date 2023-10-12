package service.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class AuthenticateRequestEntity {
    private String username;
    private String password;
    private String deviceIdentifier;
    private boolean acceptedTerms;
    private String mfaToken;

    public AuthenticateRequestEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
