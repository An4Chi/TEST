package service.inbound.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InboundRequestEntity {
    private String integrationName;
    private String endpointAlias;
    List<InboundEntity> objects;
}
