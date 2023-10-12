package service.field.form;

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
public class FormFacade {
    @Resource
    private final BaseFacade baseFacade;
    @Resource
    private final ObjectMapperConfiguration objectMapperConfiguration;
    @Resource
    private final FormConfiguration formConfiguration;


    @Autowired
    public FormFacade(BaseFacade baseFacade, ObjectMapperConfiguration objectMapperConfiguration, FormConfiguration formConfiguration) {
        this.baseFacade = baseFacade;
        this.objectMapperConfiguration = objectMapperConfiguration;
        this.formConfiguration = formConfiguration;
    }

    public FormEntity createForm(FormEntity formEntity) {
        try {
            String body = objectMapperConfiguration.getObjectMapper().writeValueAsString(formEntity);
            log.debug("form created successfully");
            return baseFacade.post(formConfiguration.getFormEndpoint(), body, FormEntity.class);
        } catch (JsonProcessingException e) {
            log.error("could not deserialize create form request entity into json");
            throw new AutomationInterruptedException(e.getMessage());
        }
    }

    public FormEntity getForm(Long formID) {
        String url = String.format(formConfiguration.getFormByIdEndpoint(), formID);
        return baseFacade.get(url, FormEntity.class);
    }

    public FormEntity updateForm(Long formID, FormEntity formEntity) {
        try {
            String body = objectMapperConfiguration.getObjectMapper().writeValueAsString(formEntity);
            String url = String.format(formConfiguration.getFormByIdEndpoint(), formID);
            log.debug("form updated successfully");
            return baseFacade.update(url, body, FormEntity.class);
        } catch (JsonProcessingException e) {
            log.error("could not deserialize create form request entity into json");
            throw new AutomationInterruptedException(e.getMessage());
        }
    }

    public FormEntity deleteForm(Long formID) {

        String url = String.format(formConfiguration.getFormByIdEndpoint(), formID);
        log.debug("form deleted successfully");
        return baseFacade.delete(url, FormEntity.class);
    }
}
