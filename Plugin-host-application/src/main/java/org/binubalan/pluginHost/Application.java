package org.binubalan.pluginHost;

import com.google.gson.JsonObject;
import org.binubalan.pluginHost.core.helper.plugin.PluginManager;
import org.binubalan.pluginHost.core.helper.plugin.TouchHostConfig;

import java.util.Arrays;

public class Application {
    private static final Application instance = new Application();

    private Application(){}

    public static Application getInstance() {
        return instance;
    }

    public void initialize() {
        PluginManager
                .getInstance()
                .loadPluginsFromTouchHostConfig();

    }

}
