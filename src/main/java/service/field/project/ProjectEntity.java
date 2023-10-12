package service.field.project;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nortecview.field.assignment.entity.Assignment;
import com.nortecview.field.contributor.entity.Contributor;
import com.nortecview.field.geometry.entity.Geometry;
import com.nortecview.field.heatnumber.entity.HeatNumberCombination;
import com.nortecview.field.material.entity.Material;
import com.nortecview.field.permission.entity.Permission;
import com.nortecview.field.project.entity.ProjectSummary;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProjectEntity {
    private Geometry geometry;
    private List<Geometry> geometries;
    private ProjectSummary summary;
    private String systemType;
    private Map<String, Object> customFields;
    private Map<String, Object> checklistCustomFields;
    private Assignment assignment;
    private List<Material> materials;
    private BigDecimal lengthInFeet;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(
            access = JsonProperty.Access.READ_ONLY
    )
    private List<Contributor> contributors;
    private Permission permission;
    private boolean deleted;
    private Boolean activeForMe;
    private List<HeatNumberCombination> heatNumberCombinations;
    @JsonIgnore
    private Integer workflowHash;
}
