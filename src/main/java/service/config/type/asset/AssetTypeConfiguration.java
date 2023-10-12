package service.config.type.asset;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@NoArgsConstructor
public class AssetTypeConfiguration {
    @Value("${endpoint.mapping.assetType}")
    private String assetTypeEndpoint;
    @Value("${endpoint.mapping.assetTypeById}")
    private String assetTypeByIdTypeEndpoint;
}
