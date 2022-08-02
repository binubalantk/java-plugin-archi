package com.binubalan.pluginarchi.pluginHost.core.helper.plugin;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 *
 */
public class TouchHostConfig {

    /**
     * Singleton instance
     */
    static final TouchHostConfig instance = new TouchHostConfig();

    /**
     * TouchHost config file name
     */
    static final String TOUCH_HOST_CONFIG_FILE = "touch-host.json";

    /**
     * TouchHost plugin property key
     */
    public static final String TOUCH_HOST_KEY_PLUGINS = "plugins";

    /**
     * Method to get the singleton instance
     *
     * @return instance
     */
    public static TouchHostConfig getInstance() {
        return instance;
    }

    /**
     * The Touch Host Configuration object
     */
    private JsonObject touchConfig;

    /**
     * Method to get the TouchHost configuration
     *
     * @return
     */
    public JsonObject getTouchConfig() {
        if (touchConfig == null) {
            touchConfig = this.readConfig();
        }
        return touchConfig;
    }

    /**
     * Method to read the Configuration from the file
     *
     * @return Config JsonObject
     */
    private JsonObject readConfig() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(TOUCH_HOST_CONFIG_FILE);
        System.out.println("loaded");
        InputStreamReader inputStreamReader = new InputStreamReader(
                Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(TOUCH_HOST_CONFIG_FILE))
        );
        return (new Gson())
                .fromJson(inputStreamReader, JsonObject.class);
    }
}
