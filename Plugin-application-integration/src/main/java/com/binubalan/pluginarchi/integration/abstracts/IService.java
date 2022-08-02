package com.binubalan.pluginarchi.integration.abstracts;

import com.binubalan.pluginarchi.integration.models.ServiceRequestModel;
import com.binubalan.pluginarchi.integration.models.ServiceResponseModel;

/**
 * Interface to define service
 *
 * @param <TRequest>  request payload type
 * @param <TResponse> response data type
 */
public interface IService
        <TRequest extends ServiceRequestModel,
                TResponse extends ServiceResponseModel> {
    /**
     * Method to execute the service execution
     *
     * @param request the request payload
     * @return the response data
     */
    TResponse onExecute(TRequest request);
}
