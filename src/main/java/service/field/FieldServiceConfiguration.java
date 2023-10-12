package service.field;

import common.BaseFacade;
import common.ObjectMapperConfiguration;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.field.asset.AssetConfiguration;
import service.field.asset.AssetFacade;
import service.field.form.FormConfiguration;
import service.field.form.FormFacade;
import service.field.project.ProjectConfiguration;
import service.field.project.ProjectFacade;


@Configuration
public class FieldServiceConfiguration {
    @Resource
    private BaseFacade baseFacade;
    @Resource
    private ObjectMapperConfiguration objectMapperConfiguration;

    @Bean
    public AssetConfiguration assetConfiguration() {
        return new AssetConfiguration();
    }

    @Bean
    public AssetFacade assetFacade(final AssetConfiguration assetConfiguration) {
        return new AssetFacade(baseFacade, objectMapperConfiguration, assetConfiguration);
    }

    @Bean
    public FormConfiguration formConfiguration() {
        return new FormConfiguration();
    }

    @Bean
    public FormFacade formFacade(final FormConfiguration formConfiguration) {
        return new FormFacade(baseFacade, objectMapperConfiguration, formConfiguration);
    }

    @Bean
    public ProjectConfiguration projectConfiguration() {
        return new ProjectConfiguration();
    }

    @Bean
    public ProjectFacade projectFacade(final ProjectConfiguration projectConfiguration) {
        return new ProjectFacade(baseFacade, objectMapperConfiguration, projectConfiguration);
    }
}
