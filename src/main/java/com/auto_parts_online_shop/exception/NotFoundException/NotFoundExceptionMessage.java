package com.auto_parts_online_shop.exception.NotFoundException;

import com.auto_parts_online_shop.exception.GlobalExceptionMessage;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Data
public class NotFoundExceptionMessage implements GlobalExceptionMessage {

    private String message;
}
