package com.adso.tienda.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
public enum OrdesStatusEnum {
    PENDIENTE(1L),
    ENVIADO(2L),
    ENTREGADO(3L),
    CANCELADO(4L);

    private final Long id;

    public Long getId(){
        return  id;
    }

    public static OrdesStatusEnum fromId(Long id){
        if (id == null) {
            return null;
        }
        for (OrdesStatusEnum status : OrdesStatusEnum.values()){
            if (status.getId().equals(id)) {
                return status;
            }
        }
        throw new IllegalArgumentException("ID de status inválido: " + id);
    }
}
