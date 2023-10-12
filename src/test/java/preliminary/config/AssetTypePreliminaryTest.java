package preliminary.config;

import com.nortecview.config.mapping.assettype.entity.AssetType;
import service.config.type.asset.AssetTypeEntity;
import org.springframework.boot.test.context.TestComponent;
import org.testng.annotations.Test;

import static preliminary.FieldsComparison.comparingFields;

@TestComponent
public class AssetTypePreliminaryTest {
    private final Class<AssetTypeEntity> assetTypeEntity = AssetTypeEntity.class;
    private final Class<AssetType> platformAssetType = AssetType.class;

    @Test
    void prerequisiteValidation() {
        comparingFields(assetTypeEntity.getClass(), platformAssetType.getClass());
    }
}
