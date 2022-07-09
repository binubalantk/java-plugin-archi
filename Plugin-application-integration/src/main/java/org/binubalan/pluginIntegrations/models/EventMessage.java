package org.binubalan.pluginIntegrations.models;

public class EventMessage<TData> {
    private String subject;
    private TData data;

    public EventMessage() {
    }

    public EventMessage(String subject, TData data) {
        this.subject = subject;
        this.data = data;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public TData getData() {
        return data;
    }

    public void setData(TData data) {
        this.data = data;
    }
}
