package com.binubalan.pluginarchi.plugin.api;

import com.binubalan.pluginarchi.integration.abstracts.IAppModule;
import com.binubalan.pluginarchi.plugin.api.models.APIModuleConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class APIModule implements IAppModule<APIModuleConfiguration> {
    private static final APIModule instance = new APIModule();

    public static IAppModule<APIModuleConfiguration> getInstance() {
        return APIModule.instance;
    }

    @Override
    public void initialize(APIModuleConfiguration configuration, String... args) {
        SpringApplication.run(APIModule.class, args);
    }
}
