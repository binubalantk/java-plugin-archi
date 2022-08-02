package com.binubalan.pluginarchi.pluginHost;

import com.binubalan.pluginarchi.plugin.api.APIModule;
import com.binubalan.pluginarchi.plugin.api.models.APIModuleConfiguration;
import com.binubalan.pluginarchi.pluginHost.core.helper.plugin.PluginManager;

/**
 * Application class
 */
public class Application {

    /**
     * Singleton instance
     */
    private static final Application instance = new Application();

    private Application() {
    }

    /**
     * Method to get the singleton instance
     *
     * @return instance
     */
    public static Application getInstance() {
        return instance;
    }

    /**
     * Method to initialize the application
     */
    public void initialize() {
        // initialize API service
        APIModule
            .getInstance()
                .initialize(new APIModuleConfiguration());
        PluginManager
                .getInstance()
                .loadPluginsFromTouchHostConfig();

    }

}
