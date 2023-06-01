package com.auto_parts_online_shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ModelDto {

    private Long id;
    private String name;
    private Long makeId;
}
