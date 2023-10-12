package service.inbound.entity;

import com.nortecview.field.geometry.entity.Datum;
import com.nortecview.field.geometry.entity.RecordType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class InboundGeometryEntity {
    private BigDecimal latitude;
    private BigDecimal longitude;
    private BigDecimal altitude;
    private Datum datum;
    private RecordType recordType;
}
