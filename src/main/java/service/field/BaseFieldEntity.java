package service.field;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nortecview.field.common.entity.FieldUser;
import io.locusview.platform.common.LocalDateTimeDeserializer;
import io.locusview.platform.common.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
abstract public class BaseFieldEntity {
    protected Long id;
    @JsonSerialize(
            using = LocalDateTimeSerializer.class
    )
    @JsonDeserialize(
            using = LocalDateTimeDeserializer.class
    )
    protected LocalDateTime createDate;
    @JsonSerialize(
            using = LocalDateTimeSerializer.class
    )
    @JsonDeserialize(
            using = LocalDateTimeDeserializer.class
    )
    protected LocalDateTime lastUpdated;
    @JsonIgnore
    protected Long createUserId;
    protected FieldUser createUser;
    @JsonIgnore
    protected Long updateUserId;
    protected FieldUser updateUser;
    protected Long projectId;
    protected Long typeId;
    protected String barcode;
    protected String uuid;
}
