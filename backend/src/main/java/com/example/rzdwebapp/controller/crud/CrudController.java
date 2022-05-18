package com.example.rzdwebapp.controller.crud;

import com.example.rzdwebapp.data.dto.PagedResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


public interface CrudController<Entity,Id> {
    @GetMapping("/headers")
    List<String> getHeaders();
    @GetMapping("/foreign-keys")
    Map<String,List> getForeignKeys();

    @GetMapping
    PagedResponse<Entity> findAllPaged (@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                        @RequestParam(value = "size", required = false, defaultValue = "50") Integer size);
    @GetMapping("/{id}")
    Entity getById(@PathVariable Id id);
    @PostMapping()
    void create(@RequestBody Entity entity);
    @PutMapping("/{id}")
    void update(@PathVariable Id id,@RequestBody Entity entity);
    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Id id);
}
