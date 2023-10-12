package service.config.type.project;

import service.config.type.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.nortecview.config.entity.Behavior;
import com.nortecview.config.entity.LineType;
import com.nortecview.config.entity.PinType;
import com.nortecview.config.layout.entity.HelpInfo;
import com.nortecview.config.layout.entity.Layout;
import com.nortecview.config.mapping.assettype.entity.AssetGeometryType;
import com.nortecview.config.projecttype.entity.EntityDependency;
import com.nortecview.config.projecttype.entity.MaterialBehavior;
import io.locusview.platform.entity.EntityType;
import lombok.*;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ProjectTypeEntity extends BaseEntity {
    private Integer order;
    private Behavior behavior;
    private Layout checklistLayout;
    private HelpInfo helpInfo;
    private Boolean rootType;
    private Boolean mappable;
    private AssetGeometryType geometryType;
    Map<EntityType, List<EntityDependency>> components;
    private PinType pinType;
    private LineType lineType;
    private Long workflowId;
    @JsonIgnore
    private Boolean deleted;
    @JsonSetter(nulls = Nulls.SKIP)
    private Boolean active = true;
    private MaterialBehavior materialBehavior;
    private Integer workflowHash;
}
