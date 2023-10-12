package preliminary;

import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;

import java.lang.reflect.Field;
import java.util.Arrays;

@Slf4j
public class FieldsComparison {
    public static <T, U> void comparingFields(Class<T> automationEntity, Class<U> platformEntity) {
        Arrays.asList(automationEntity.getDeclaredFields()).stream()
                .map(Field::getName)
                .forEach(fieldName ->
                {
                    try {
                        Field platformField = platformEntity.getDeclaredField(fieldName);
                        Assert.assertNotNull(platformField, String.format("the property %s does not exist in %s pleas align the auto entity to the platform entity", fieldName, automationEntity));
                    } catch (NoSuchFieldException noSuchFieldException) {
                        Assert.fail(String.format("The fields cannot be compared, the logs must be checked exception %s", noSuchFieldException));
                    }
                });
    }
}
