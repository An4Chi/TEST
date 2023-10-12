package service.field.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@NoArgsConstructor
public class FormConfiguration {
    @Value("${endpoint.field.forms}")
    private String formEndpoint;
    @Value("${endpoint.field.formById}")
    private String formByIdEndpoint;
}
