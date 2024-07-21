package top.season66.itea.actuator;

import top.season66.itea.ITeaProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;
import top.season66.itea.ITeaProperties;

@Component
@Endpoint(id = "shop")
public class ShopEndpoint {
    private ITeaProperties iTeaProperties;

    public ShopEndpoint(
            ObjectProvider<ITeaProperties> iTeaProperties) {
        this.iTeaProperties = iTeaProperties.getIfAvailable();
    }

    @ReadOperation
    public String state() {
        if (iTeaProperties == null || !iTeaProperties.isReady()) {
            return "We're not ready.";
        } else {
            return "We open " + iTeaProperties.getOpenHours() + ".";
        }
    }
}
