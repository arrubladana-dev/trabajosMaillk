package com.adso.listatareas.enums;

public enum PriorityTask {
    BAJA("baja"), 
    MEDIA("media"), 
    ALTA("alta"), 
    URGENTE("urgente");

    private final String description;

    PriorityTask(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
