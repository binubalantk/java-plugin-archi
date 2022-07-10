package org.binubalan.pluginHost.core.helper.plugin;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.binubalan.pluginIntegrations.abstracts.IPluginManager;

import java.util.List;
import java.util.Objects;

/**
 * Plugin manager, implements @IPluginManger
 */
public class PluginManager implements IPluginManager {

    /**
     * Singleton instance of the PluginManager
     */
    private static final PluginManager instance = new PluginManager();

    private PluginManager() {
    }

    /**
     * Method to get the singleton instance
     *
     * @return the isntance
     */
    public static PluginManager getInstance() {
        return instance;
    }

    /**
     * Method to load plugins from the list of URIs
     *
     * @param pluginUris list of plugin URIs
     */
    public void loadPlugins(List<String> pluginUris) {
        pluginUris.forEach(this::loadPlugin);
    }

    /**
     * Method to load plugins from a TouchHost configuration
     *
     * @param touchHostConfig the touch host configuration object
     */
    public void loadPlugins(JsonObject touchHostConfig) {
        JsonArray pluginArray = touchHostConfig
                .get(TouchHostConfig.TOUCH_HOST_KEY_PLUGINS)
                .getAsJsonArray();
        pluginArray.forEach(jsonElement -> this.loadPlugin(jsonElement.getAsString()));
    }

    /**
     * Method to load plugins from the touchHost configuration entries
     */
    public void loadPluginsFromTouchHostConfig() {
        JsonObject hostConfig = TouchHostConfig
                .getInstance()
                .getTouchConfig();
        if (hostConfig == null || !hostConfig.has(TouchHostConfig.TOUCH_HOST_KEY_PLUGINS)) {
            System.out.println("No plugins mentioned in touch host configuration");
        }
        this.loadPlugins(Objects.requireNonNull(hostConfig));
    }

    /**
     * Method to load plugin from a plugin URI
     *
     * @param pluginUri Plugin jar file URI
     */
    void loadPlugin(String pluginUri) {
        // load the plugin with the loader
        try {
            PluginLoader
                    .getInstance()
                    .load(pluginUri);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

