package com.auto_parts_online_shop.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PartDto {

    @NotNull
    private String name;
    private String description;

    @NotNull
    private Float price;

    @NotNull
    private Long categoryId;

    @NotNull
    private List<Long> modelIds;
}
