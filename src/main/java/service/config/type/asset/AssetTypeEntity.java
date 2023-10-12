package service.config.type.asset;

import service.config.type.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.nortecview.config.entity.Behavior;
import com.nortecview.config.entity.LineType;
import com.nortecview.config.entity.PinType;
import com.nortecview.config.layout.entity.HelpInfo;
import com.nortecview.config.mapping.assettype.entity.AssetGeometryType;
import com.nortecview.config.mapping.assettype.entity.BarcodeType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AssetTypeEntity extends BaseEntity {
    private Integer order;
    private AssetGeometryType geometryType;
    private Integer maxJoints;
    private HelpInfo helpInfo;
    private Behavior behavior;
    private Boolean partOfCatalog;
    private Boolean immediateMap;
    private BarcodeType barcodeSupport;
    private String barcodeRegex;
    private PinType pinType;
    private LineType lineType;
    @JsonIgnore
    private Boolean deleted;
    @JsonSetter(nulls = Nulls.SKIP)
    private Boolean active = true;
    private boolean partOfCu;
    private boolean partOfMachine;

}
