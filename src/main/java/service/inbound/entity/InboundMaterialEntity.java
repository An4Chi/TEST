package service.inbound.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InboundMaterialEntity {
    private String partNumber;
    private ProjectStatus status;
    private Double quantity;
}
