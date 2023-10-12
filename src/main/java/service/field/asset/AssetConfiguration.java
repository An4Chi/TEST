package service.field.asset;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@NoArgsConstructor
public class AssetConfiguration {
    @Value("${endpoint.field.asset}")
    private String assetEndpoint;
    @Value("${endpoint.field.assetById}")
    private String assetByIdEndpoint;
    @Value("${endpoint.field.assetByParam}")
    private String assetByParamEndpoint;
}
