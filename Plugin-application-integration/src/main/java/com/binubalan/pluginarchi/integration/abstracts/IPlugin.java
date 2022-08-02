package com.binubalan.pluginarchi.integration.abstracts;

/**
 * Interface to define the plugin boot-start
 */
public interface IPlugin {

    /**
     * Method to initialize the boot-start
     *
     * @param services the service interfaces
     */
    void onInitialized(IService[] services);

    /**
     * Method will be called after plugin mounted
     */
    void onMounted();

    /**
     * Method will be called before un-mounting the plugin
     */
    void onUnMount();
}
