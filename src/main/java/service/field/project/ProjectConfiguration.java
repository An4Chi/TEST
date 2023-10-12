package service.field.project;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@NoArgsConstructor
public class ProjectConfiguration {
    @Value("${endpoint.field.projects}")
    private String projectEndpoint;
    @Value("${endpoint.field.projectById}")
    private String projectByIdEndpoint;
    @Value("${endpoint.field.projectByParam}")
    private String projectByParamEndpoint;
}
