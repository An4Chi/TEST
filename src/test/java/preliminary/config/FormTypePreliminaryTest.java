package preliminary.config;

import com.nortecview.config.form.formtype.entity.FormType;
import service.config.type.form.FormTypeEntity;
import org.testng.annotations.Test;
import org.springframework.boot.test.context.TestComponent;

import static preliminary.FieldsComparison.comparingFields;

@TestComponent
public class FormTypePreliminaryTest {
    private final Class<FormTypeEntity> formTypeEntity = FormTypeEntity.class;
    private final Class<FormType> platformFormType = FormType.class;

    @Test
    void prerequisiteValidation() {
        comparingFields(formTypeEntity.getClass(), platformFormType.getClass());
    }
}
