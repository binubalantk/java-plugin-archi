package com.binubalan.pluginarchi.integration.models;

/**
 * Event Message
 *
 * @param <TData> payload data type
 */
public class EventMessage<TData> {

    /**
     * Subject to subscribe
     */
    private String subject;

    /**
     * the data
     */
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
