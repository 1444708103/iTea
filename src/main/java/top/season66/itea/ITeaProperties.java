package top.season66.itea;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties("itea")
public class ITeaProperties {
    private boolean ready;
    private String openHours;

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public String getOpenHours() {
        return openHours;
    }

    public void setOpenHours(String openHours) {
        this.openHours = openHours;
    }
}
