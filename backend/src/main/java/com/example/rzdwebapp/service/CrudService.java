package com.example.rzdwebapp.service;

import org.springframework.data.domain.Page;

public interface CrudService<Entity,Id> {
    Page<Entity> findAllPaged(Integer page, Integer size);
    Entity getById(Id id);
    Entity create(Entity entity);
    Entity update(Id id, Entity entity);
    void deleteById(Id id);
}
