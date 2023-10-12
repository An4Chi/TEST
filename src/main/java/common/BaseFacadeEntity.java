package common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@PropertySource("classpath:config.properties")
public class BaseFacadeEntity {

    @Value("${baseUrl}")
    private String baseUrl;
}
