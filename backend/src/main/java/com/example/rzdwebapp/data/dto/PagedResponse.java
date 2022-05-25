package com.example.rzdwebapp.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
public class PagedResponse<Entity> {
    public PagedResponse(Page<Entity> page) {
        this.entities = page.getContent();
        this.countPages = page.getTotalPages();
    }
    private List<Entity> entities;
    private Integer countPages;
}
