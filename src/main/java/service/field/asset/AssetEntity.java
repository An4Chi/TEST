package service.field.asset;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nortecview.field.asset.entity.EdgeType;
import com.nortecview.field.asset.entity.InputMethod;
import com.nortecview.field.asset.entity.Warning;
import com.nortecview.field.externallinks.entity.ExternalLink;
import com.nortecview.field.geometry.entity.Geometry;
import com.nortecview.field.photo.Photo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AssetEntity {
    private InputMethod inputMethod;
    private List<Photo> photos;
    private List<Warning> warnings;
    private BigDecimal lengthInFeet;
    private Long parentId;
    private int order;
    private EdgeType edgeType;
    private List<Geometry> geometries;
    private String systemType;
    private Map<String, Object> customFields;
    private List<ExternalLink> esriLinks;
    private boolean deleted;
    private boolean excluded;
    @JsonIgnore
    private String attachmentName;
    protected Long parentProjectId;
}
