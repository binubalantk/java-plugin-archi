package com.binubalan.pluginarchi.example.plugin.touch;

import com.binubalan.pluginarchi.integration.abstracts.IPlugin;
import com.binubalan.pluginarchi.integration.abstracts.IService;

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
        System.out.println("Zephyre plugin V 1.0");
    }

    @Override
    public void onUnMount() {
        System.out.println("Plugin: Thank you");
    }
}
