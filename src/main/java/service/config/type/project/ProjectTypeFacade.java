package service.config.type.project;

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
public class ProjectTypeFacade {
    @Resource
    private final BaseFacade baseFacade;
    @Resource
    private final ObjectMapperConfiguration objectMapperConfiguration;
    @Resource
    private final ProjectTypeConfiguration projectTypeConfiguration;

    @Autowired
    public ProjectTypeFacade(BaseFacade baseFacade, ObjectMapperConfiguration objectMapperConfiguration, ProjectTypeConfiguration projectTypeConfiguration) {
        this.baseFacade = baseFacade;
        this.objectMapperConfiguration = objectMapperConfiguration;
        this.projectTypeConfiguration = projectTypeConfiguration;
    }

    public ProjectTypeEntity createProjectType(ProjectTypeEntity projectTypeEntity) {
        try {
            final String body = objectMapperConfiguration.getObjectMapper().writeValueAsString(projectTypeEntity);
            log.debug("project type created successfully");
            return baseFacade.post(projectTypeConfiguration.getProjectTypeEndpoint(), body, ProjectTypeEntity.class);

        } catch (JsonProcessingException e) {
            log.error("could not deserialize create project request entity into json");
            throw new AutomationInterruptedException(e.getMessage());
        }
    }

    public ProjectTypeEntity getProjectType(Long projectID) {
        final String url = String.format(projectTypeConfiguration.getProjectTypeByIdEndpoint(), projectID);
        return baseFacade.get(url, ProjectTypeEntity.class);
    }

    public ProjectTypeEntity updateProjectType(Long projectID, ProjectTypeEntity projectTypeEntity) {
        try {
            final String body = objectMapperConfiguration.getObjectMapper().writeValueAsString(projectTypeEntity);
            final String url = String.format(projectTypeConfiguration.getProjectTypeByIdEndpoint(), projectID);
            log.debug("project type updated successfully");
            return baseFacade.update(url, body, ProjectTypeEntity.class);
        } catch (JsonProcessingException e) {
            log.error("could not deserialize create project request entity into json");
            throw new AutomationInterruptedException(e.getMessage());
        }
    }

    public ProjectTypeEntity deleteProjectType(Long projectID) {
        final String url = String.format(projectTypeConfiguration.getProjectTypeByIdEndpoint(), projectID);
        log.debug("project type deleted successfully");
        return baseFacade.delete(url, ProjectTypeEntity.class);
    }
}
