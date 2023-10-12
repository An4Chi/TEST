package service.config.type.project;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@NoArgsConstructor
public class ProjectTypeConfiguration {
    @Value("${endpoint.project.types}")
    private String projectTypeEndpoint;
    @Value("${endpoint.project.typesById}")
    private String projectTypeByIdEndpoint;
}
