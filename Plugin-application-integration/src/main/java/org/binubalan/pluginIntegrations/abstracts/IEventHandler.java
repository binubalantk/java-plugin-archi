package org.binubalan.pluginIntegrations.abstracts;

import org.binubalan.pluginIntegrations.models.EventMessage;

public interface IEventHandler<TData, TMessage extends EventMessage<TData>> {
    boolean publish(TMessage message);
    void onMessageReceived(TMessage message);
}
