package com.auto_parts_online_shop.dto;

import lombok.*;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Data
public class Message {

    private String title;
    private String message;
}
