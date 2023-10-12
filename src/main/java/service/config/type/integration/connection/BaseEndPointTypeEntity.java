package service.config.type.integration.connection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
abstract public class BaseEndPointTypeEntity {
    protected Long id;
    @JsonIgnore
    protected LocalDateTime createDate;
    @JsonIgnore
    protected LocalDateTime lastUpdated;
    protected Long connectionId;
}
