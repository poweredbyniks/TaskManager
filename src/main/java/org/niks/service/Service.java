package org.niks.service;

public abstract class Service <T> {
    public abstract void create(T entity);
    public abstract void list();
    public abstract void remove(String taskToRemove);
    public abstract void clear();
}
