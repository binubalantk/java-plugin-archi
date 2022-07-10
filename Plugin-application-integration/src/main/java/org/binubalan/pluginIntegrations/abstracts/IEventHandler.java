package org.binubalan.pluginIntegrations.abstracts;

import org.binubalan.pluginIntegrations.models.EventMessage;

/**
 * Interface to handle Events
 *
 * @param <TData>    the data type
 * @param <TMessage> the message payload type
 */
public interface IEventHandler<TData, TMessage extends EventMessage<TData>> {

    /**
     * Method to publish message to a subject
     *
     * @param message the message payload
     * @return True if success, False otherwise
     */
    boolean publish(TMessage message);

    /**
     * Method to subscribe subject
     *
     * @param message the incoming message payload
     */
    void onMessageReceived(TMessage message);
}
