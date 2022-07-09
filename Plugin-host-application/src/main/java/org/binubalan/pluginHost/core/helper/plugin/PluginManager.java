package org.binubalan.pluginHost.core.helper.plugin;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.binubalan.pluginIntegrations.abstracts.IPluginManger;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class PluginManager implements IPluginManger {
    private static final PluginManager instance = new PluginManager();

    private PluginManager(){
    }

    public static PluginManager getInstance() {
        return instance;
    }

    public void loadPlugins(List<String> pluginUris) {
        pluginUris.forEach(this::loadPlugin);
    }

    public void loadPlugins(JsonObject touchHostConfig) {
        JsonArray pluginArray = touchHostConfig
                .get(TouchHostConfig.TOUCH_HOST_KEY_PLUGINS)
                .getAsJsonArray();
        pluginArray.forEach(jsonElement -> this.loadPlugin(jsonElement.getAsString()));
    }

    public void loadPluginsFromTouchHostConfig(){
        JsonObject hostConfig = TouchHostConfig
                .getInstance()
                .getTouchConfig();
        if(hostConfig == null || !hostConfig.has(TouchHostConfig.TOUCH_HOST_KEY_PLUGINS)){
            System.out.println("No plugins mentioned in touch host configuration");
        }
        this.loadPlugins(Objects.requireNonNull(hostConfig));
    }

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

