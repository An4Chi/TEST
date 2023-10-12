package preliminary.config;

import com.nortecview.config.projecttype.entity.ProjectType;
import service.config.type.project.ProjectTypeEntity;
import org.springframework.boot.test.context.TestComponent;
import org.testng.annotations.Test;

import static preliminary.FieldsComparison.comparingFields;

@TestComponent
public class ProjectTypePreliminaryTest {
    private final Class<ProjectTypeEntity> projectTypeEntity = ProjectTypeEntity.class;
    private final Class<ProjectType> platformProjectType = ProjectType.class;

    @Test
    void prerequisiteValidation() {
        comparingFields(projectTypeEntity.getClass(), platformProjectType.getClass());
    }

}
