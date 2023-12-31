package service.config.type.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@NoArgsConstructor
public class FormTypeConfiguration {
    @Value("${endpoint.mapping.formType}")
    private String formTypeEndpoint;
    @Value("${endpoint.mapping.formTypeById}")
    private String formTypeByIdEndpoint;
}
