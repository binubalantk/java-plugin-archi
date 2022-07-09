package org.binubalan.pluginIntegrations.abstracts;

public interface IPlugin {
    void onInitialized(IService[] services);
    void onMounted();
    void onUnMount();
}
