package com.binubalan.pluginarchi.integration.abstracts;

public interface IAppModule<TConfig> {
    void initialize(TConfig configuration, String...args);
}
