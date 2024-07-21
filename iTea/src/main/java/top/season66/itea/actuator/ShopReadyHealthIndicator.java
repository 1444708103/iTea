package top.season66.itea.actuator;

import top.season66.itea.ITeaProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;
import top.season66.itea.ITeaProperties;

@Component
public class ShopReadyHealthIndicator extends AbstractHealthIndicator {
    private ITeaProperties iTeaProperties;

    public ShopReadyHealthIndicator(
            ObjectProvider<ITeaProperties> iTeaProperties) {
        this.iTeaProperties = iTeaProperties.getIfAvailable();
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        if (iTeaProperties == null || !iTeaProperties.isReady()) {
            builder.down();
        } else {
            builder.up();
        }
    }
}
