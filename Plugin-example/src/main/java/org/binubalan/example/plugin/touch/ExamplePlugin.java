package org.binubalan.example.plugin.touch;

import org.binubalan.pluginIntegrations.abstracts.IPlugin;
import org.binubalan.pluginIntegrations.abstracts.IService;

/**
 * Example plugin bootstrap class implements the IPlugin interface
 */
public class ExamplePlugin implements IPlugin {
    @Override
    public void onInitialized(IService... services) {
        System.out.println("Plugin: Initialized");
    }

    @Override
    public void onMounted() {
        System.out.println("This is an example plugin V 1.0");
    }

    @Override
    public void onUnMount() {
        System.out.println("Plugin: Thank you");
    }
}
