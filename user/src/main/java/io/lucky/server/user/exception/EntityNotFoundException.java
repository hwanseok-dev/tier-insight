package io.lucky.server.user.exception;

import io.lucky.server.user.entity.BaseEntity;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Class<? extends BaseEntity> entity, Long id){
        super(String.format("class : %s, id : %s", entity.getSimpleName(), id));
    }
}
