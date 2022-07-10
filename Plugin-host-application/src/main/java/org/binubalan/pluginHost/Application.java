package org.binubalan.pluginHost;

import org.binubalan.pluginHost.core.helper.plugin.PluginManager;

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
        PluginManager
                .getInstance()
                .loadPluginsFromTouchHostConfig();

    }

}
