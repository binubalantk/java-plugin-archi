package org.binubalan.pluginIntegrations.abstracts;

import org.binubalan.pluginIntegrations.models.ServiceRequestModel;
import org.binubalan.pluginIntegrations.models.ServiceResponseModel;

public interface IService
        <TRequest extends ServiceRequestModel,
                TResponse extends ServiceResponseModel> {
    TResponse onExecute(TRequest request);
}
