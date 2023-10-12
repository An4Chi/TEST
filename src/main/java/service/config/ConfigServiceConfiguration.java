package service.config;

import common.BaseFacade;
import common.ObjectMapperConfiguration;
import service.config.type.asset.AssetTypeConfiguration;
import service.config.type.asset.AssetTypeFacade;
import service.config.type.form.FormTypeConfiguration;
import service.config.type.form.FormTypeFacade;
import service.config.type.integration.connection.ConnectionConfiguration;
import service.config.type.integration.connection.ConnectionFacade;
import service.config.type.integration.integration.IntegrationConfiguration;
import service.config.type.integration.integration.IntegrationFacade;
import service.config.type.project.ProjectTypeConfiguration;
import service.config.type.project.ProjectTypeFacade;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigServiceConfiguration {
    @Resource
    private BaseFacade baseFacade;
    @Resource
    private ObjectMapperConfiguration objectMapperConfiguration;

    @Bean
    public ProjectTypeConfiguration projectTypeConfiguration() {
        return new ProjectTypeConfiguration();
    }

    @Bean
    public AssetTypeConfiguration assetTypeConfiguration() {
        return new AssetTypeConfiguration();
    }

    @Bean
    public FormTypeConfiguration formTypeConfiguration() {
        return new FormTypeConfiguration();
    }

    @Bean
    public ConnectionConfiguration connectionConfiguration() {
        return new ConnectionConfiguration();
    }

    @Bean
    public IntegrationConfiguration integrationConfiguration() {
        return new IntegrationConfiguration();
    }

    @Bean
    public ProjectTypeFacade projectTypeFacade(final ProjectTypeConfiguration projectTypeConfiguration) {
        return new ProjectTypeFacade(baseFacade, objectMapperConfiguration, projectTypeConfiguration);
    }

    @Bean
    public AssetTypeFacade assetTypeFacade(final AssetTypeConfiguration assetTypeConfiguration) {
        return new AssetTypeFacade(baseFacade, objectMapperConfiguration, assetTypeConfiguration);
    }

    @Bean
    public FormTypeFacade formTypeFacade(final FormTypeConfiguration formTypeConfiguration) {
        return new FormTypeFacade(baseFacade, objectMapperConfiguration, formTypeConfiguration);
    }

    @Bean
    public IntegrationFacade integrationFacade(final IntegrationConfiguration integrationConfiguration) {
        return new IntegrationFacade(baseFacade, objectMapperConfiguration, integrationConfiguration);
    }

    @Bean
    public ConnectionFacade connectionTypeFacade(final ConnectionConfiguration connectionConfiguration) {
        return new ConnectionFacade(baseFacade, objectMapperConfiguration, connectionConfiguration);
    }
}
