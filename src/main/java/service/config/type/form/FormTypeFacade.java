package service.config.type.form;

import com.fasterxml.jackson.core.JsonProcessingException;
import common.exceptions.AutomationInterruptedException;
import common.BaseFacade;
import common.ObjectMapperConfiguration;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FormTypeFacade {
    @Resource
    private final BaseFacade baseFacade;
    @Resource
    private final ObjectMapperConfiguration objectMapperConfiguration;
    @Resource
    private final FormTypeConfiguration formTypeConfiguration;


    @Autowired
    public FormTypeFacade(BaseFacade baseFacade, ObjectMapperConfiguration objectMapperConfiguration, FormTypeConfiguration formTypeConfiguration) {
        this.baseFacade = baseFacade;
        this.objectMapperConfiguration = objectMapperConfiguration;
        this.formTypeConfiguration = formTypeConfiguration;
    }

    public FormTypeEntity createFormType(FormTypeEntity formTypeEntity) {
        try {
            final String body = objectMapperConfiguration.getObjectMapper().writeValueAsString(formTypeEntity);
            log.debug("form type created successfully");
            return baseFacade.post(formTypeConfiguration.getFormTypeEndpoint(), body, FormTypeEntity.class);
        } catch (JsonProcessingException e) {
            log.error("could not deserialize create form request entity into json");
            throw new AutomationInterruptedException(e.getMessage());
        }

    }

    public FormTypeEntity getFormType(Long formTypeID) {
        String url = String.format(formTypeConfiguration.getFormTypeByIdEndpoint(), formTypeID);
        return baseFacade.get(url, FormTypeEntity.class);
    }

    public FormTypeEntity updateFormType(Long formTypeID, FormTypeEntity formTypeEntity) {
        try {
            final String body = objectMapperConfiguration.getObjectMapper().writeValueAsString(formTypeEntity);
            final String url = String.format(formTypeConfiguration.getFormTypeByIdEndpoint(), formTypeID);
            log.debug("form type updated successfully");
            return baseFacade.update(url, body, FormTypeEntity.class);
        } catch (JsonProcessingException e) {
            log.error("could not deserialize create form request entity into json");
            throw new AutomationInterruptedException(e.getMessage());
        }
    }

    public FormTypeEntity deleteFormType(Long formTypeID) {

        final String url = String.format(formTypeConfiguration.getFormTypeByIdEndpoint(), formTypeID);
        log.debug("form type deleted successfully");
        return baseFacade.delete(url, FormTypeEntity.class);
    }
}
