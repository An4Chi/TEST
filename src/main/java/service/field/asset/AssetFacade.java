package service.field.asset;

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
public class AssetFacade {
    @Resource
    private final BaseFacade baseFacade;
    @Resource
    private final ObjectMapperConfiguration objectMapperConfiguration;
    @Resource
    private final AssetConfiguration assetConfiguration;

    @Autowired
    public AssetFacade(BaseFacade baseFacade, ObjectMapperConfiguration objectMapperConfiguration, AssetConfiguration assetConfiguration) {
        this.baseFacade = baseFacade;
        this.objectMapperConfiguration = objectMapperConfiguration;
        this.assetConfiguration = assetConfiguration;
    }

    public AssetEntity createAsset(AssetEntity assetEntity) {

        try {
            String body = objectMapperConfiguration.getObjectMapper().writeValueAsString(assetEntity);
            log.debug("asset created successfully");
            return baseFacade.post(assetConfiguration.getAssetEndpoint(), body, AssetEntity.class);
        } catch (JsonProcessingException e) {
            log.error("could not deserialize create asset request entity into json");
            throw new AutomationInterruptedException(e.getMessage());
        }
    }

    public AssetEntity getAsset(Long assetId) {
        String url = String.format(assetConfiguration.getAssetByIdEndpoint(), assetId);
        return baseFacade.get(url, AssetEntity.class);

    }

    public AssetEntity updateAsset(Long assetID, AssetEntity assetEntity) {
        try {
            String body = objectMapperConfiguration.getObjectMapper().writeValueAsString(assetEntity);
            String url = String.format(assetConfiguration.getAssetByIdEndpoint(), assetID);
            log.debug("asset updated successfully");
            return baseFacade.update(url, body, AssetEntity.class);
        } catch (JsonProcessingException e) {
            log.error("could not deserialize create asset request entity into json");
            throw new AutomationInterruptedException(e.getMessage());
        }
    }

    public AssetEntity deleteAsset(Long assetID) {
        String url = String.format(assetConfiguration.getAssetByIdEndpoint(), assetID);
        log.debug("asset deleted successfully");
        return baseFacade.delete(url, AssetEntity.class);
    }
}
