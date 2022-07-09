package org.binubalan.pluginHost.core.helper.plugin;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Objects;

public class TouchHostConfig {
    static final TouchHostConfig instance = new TouchHostConfig();

    static final String TOUCH_HOST_CONFIG_FILE = "touch-host.json";

    public static final String TOUCH_HOST_KEY_PLUGINS = "plugins";

    public static TouchHostConfig getInstance() {
        return instance;
    }

    private JsonObject touchConfig;

    public JsonObject getTouchConfig() {
        if(touchConfig == null) {
            touchConfig = this.readConfig();
        }
        return touchConfig;
    }

    private JsonObject readConfig() {
        InputStreamReader inputStreamReader = new InputStreamReader(
                Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(TOUCH_HOST_CONFIG_FILE))
        );
        return (new Gson())
                .fromJson(inputStreamReader, JsonObject.class);
    }
}
