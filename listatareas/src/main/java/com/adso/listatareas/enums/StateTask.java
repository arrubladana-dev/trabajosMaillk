package com.adso.listatareas.enums;


public enum StateTask {
    PENDIENTE("pendiente"), 
    EN_PROGRESO("en_progreso"), 
    COMPLETADA("completada"), 
    CANCELADA("cancelada");

    private final String description;

    StateTask(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
