package service.config.type.form;

import service.config.type.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.nortecview.config.entity.Behavior;
import com.nortecview.config.layout.entity.HelpInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FormTypeEntity extends BaseEntity {
    private Integer order;
    private HelpInfo helpInfo;
    private Behavior behavior;
    @JsonIgnore
    private Boolean deleted;
    private Boolean attachable;
    @JsonSetter(nulls = Nulls.SKIP)
    private Boolean active = true;
    private boolean captureLocation;
    private boolean partOfCu;
}
