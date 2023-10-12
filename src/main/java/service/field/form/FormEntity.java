package service.field.form;

import com.nortecview.field.geometry.entity.Geometry;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import service.field.BaseFieldEntity;

import java.util.Map;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FormEntity extends BaseFieldEntity {
    private Geometry geometry;
    private Map<String, Object> customFields;
    private boolean deleted;
    protected Long parentProjectId;

}
