package IntegrationManagerTests;

import com.nortecview.config.layout.entity.FieldType;
import com.nortecview.config.layout.entity.Layout;
import com.nortecview.config.layout.entity.Section;
import com.nortecview.config.sharedlist.entity.ListType;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class CustomFieldHandler {
    public record CustomField<T>(String title, String key, Long id, T value) {

        public static <T> CustomField<T> createFrom(final FieldType fieldType, final Layout layout, final T value) {
            final String fieldTitle = fieldType.name();
            final Pair<String, Long> pair = extractCustomFieldKeyAndId(layout, fieldTitle);
            return new CustomField<>(fieldTitle, pair.getKey(), pair.getValue(), value);
        }

        public static <T> CustomField<T> createFromList(final FieldType fieldType, final ListType listType, final Layout layout, final T value) {
            final String fieldTitle = listType + fieldType.name();
            final Pair<String, Long> pair = extractCustomFieldKeyAndId(layout, fieldTitle);
            return new CustomField<>(fieldTitle, pair.getKey(), pair.getValue(), value);
        }
    }

    public static Pair<String, Long> extractCustomFieldKeyAndId(final Layout layout, final String customFieldName) {
        return layout.getSections().stream()
                .map(Section::getFields)
                .flatMap(List::stream)
                .filter(field -> field.getTitle().contains(customFieldName))
                .map(field -> Pair.of(field.getKey(), field.getId()))
                .findFirst().orElseThrow();
    }


}
