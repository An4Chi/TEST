package service.config.type.integration.connection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nortecview.config.existing.source.entity.AuthenticationParams;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class LocalConnectionEntity {
    private Long id;
    @JsonIgnore
    private LocalDateTime createDate;
    @JsonIgnore
    private LocalDateTime lastUpdated;
    private Long connectionId;
    private String domain;
    private AuthenticationParams authenticationParams;
    private boolean active;
    @JsonIgnore
    private boolean deleted;
}
