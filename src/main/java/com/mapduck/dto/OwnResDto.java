package com.mapduck.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnResDto {
    private Long ownId;
    private LocalDateTime purchaseAt;
    private LocalDateTime createdAt;
    private ProductDto ownProduct;
}
