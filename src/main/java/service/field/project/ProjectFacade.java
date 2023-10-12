package service.field.project;

import com.fasterxml.jackson.core.JsonProcessingException;
import common.exceptions.AutomationInterruptedException;
import common.BaseFacade;
import common.ObjectMapperConfiguration;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("classpath:config.properties")
@Slf4j
@Component
public class ProjectFacade {
    @Resource
    private final BaseFacade baseFacade;
    @Resource
    private final ObjectMapperConfiguration objectMapperConfiguration;
    @Resource
    private final ProjectConfiguration projectConfiguration;


    @Autowired
    public ProjectFacade(BaseFacade baseFacade, ObjectMapperConfiguration objectMapperConfiguration, ProjectConfiguration projectConfiguration) {
        this.baseFacade = baseFacade;
        this.objectMapperConfiguration = objectMapperConfiguration;
        this.projectConfiguration = projectConfiguration;
    }

    public ProjectEntity createProject(ProjectEntity projectEntity) {
        try {
            String body = objectMapperConfiguration.getObjectMapper().writeValueAsString(projectEntity);
            log.debug("project created successfully");
            return baseFacade.post(projectConfiguration.getProjectEndpoint(), body, ProjectEntity.class);

        } catch (JsonProcessingException e) {
            log.error("could not deserialize create project request entity into json");
            throw new AutomationInterruptedException(e.getMessage());
        }
    }

    public ProjectEntity getProject(Long projectID) {

        String url = String.format(projectConfiguration.getProjectByIdEndpoint(), projectID);
        return baseFacade.get(url, ProjectEntity.class);
    }

    public ProjectEntity updateProject(Long projectID, ProjectEntity projectEntity) {
        try {
            String body = objectMapperConfiguration.getObjectMapper().writeValueAsString(projectEntity);
            String url = String.format(projectConfiguration.getProjectByIdEndpoint(), projectID);
            log.debug("project updated successfully");
            return baseFacade.update(url, body, ProjectEntity.class);
        } catch (JsonProcessingException e) {
            log.error("could not deserialize create project request entity into json");
            throw new AutomationInterruptedException(e.getMessage());
        }
    }

    public ProjectEntity deleteProject(Long projectID) {
        String url = String.format(projectConfiguration.getProjectByIdEndpoint(), projectID);
        log.debug("project deleted successfully");
        return baseFacade.delete(url, ProjectEntity.class);
    }
}
