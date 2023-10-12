package service.config.type.asset;

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
public class AssetTypeFacade {
    @Resource
    private final BaseFacade baseFacade;
    @Resource
    private final ObjectMapperConfiguration objectMapperConfiguration;
    @Resource
    private final AssetTypeConfiguration assetTypeConfiguration;


    @Autowired
    public AssetTypeFacade(BaseFacade baseFacade, ObjectMapperConfiguration objectMapperConfiguration, AssetTypeConfiguration assetTypeConfiguration) {
        this.baseFacade = baseFacade;
        this.objectMapperConfiguration = objectMapperConfiguration;
        this.assetTypeConfiguration = assetTypeConfiguration;
    }

    public AssetTypeEntity createAssetType(AssetTypeEntity assetTypeEntity) {
        try {
            final String body = objectMapperConfiguration.getObjectMapper().writeValueAsString(assetTypeEntity);
            log.debug("asset type created successfully");
            return baseFacade.post(assetTypeConfiguration.getAssetTypeEndpoint(), body, AssetTypeEntity.class);
        } catch (JsonProcessingException e) {
            log.error("could not deserialize create asset request entity into json");
            throw new AutomationInterruptedException(e.getMessage());
        }


    }

    public AssetTypeEntity getAssetType(Long assetTypeId) {
        final String url = String.format(assetTypeConfiguration.getAssetTypeByIdTypeEndpoint(), assetTypeId);
        return baseFacade.get(url, AssetTypeEntity.class);

    }

    public AssetTypeEntity updateAssetType(Long assetTypeID, AssetTypeEntity assetTypeEntity) {
        try {
            final String body = objectMapperConfiguration.getObjectMapper().writeValueAsString(assetTypeEntity);
            final String url = String.format(assetTypeConfiguration.getAssetTypeByIdTypeEndpoint(), assetTypeID);
            log.debug("asset type updated successfully");
            return baseFacade.update(url, body, AssetTypeEntity.class);
        } catch (JsonProcessingException e) {
            log.error("could not deserialize create asset request entity into json");
            throw new AutomationInterruptedException(e.getMessage());
        }


    }

    public AssetTypeEntity deleteAssetType(Long assetTypeID) {

        String url = String.format(assetTypeConfiguration.getAssetTypeByIdTypeEndpoint(), assetTypeID);
        log.debug("asset type deleted successfully");
        return baseFacade.delete(url, AssetTypeEntity.class);

    }
}
