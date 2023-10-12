package service.config.type;

import com.nortecview.config.entity.PreviewCard;
import com.nortecview.config.layout.entity.Dependencies;
import com.nortecview.config.layout.entity.Layout;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
abstract public class BaseEntity {
    protected Long id;
    protected String title;
    protected Layout layout;
    protected Dependencies visibleWhen;
    protected Dependencies requireWhen;
    protected PreviewCard previewCard;
    protected String symbol;
}
