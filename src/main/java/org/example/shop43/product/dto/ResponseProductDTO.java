package org.example.shop43.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResponseProductDTO {
    private Long id;
    private String title;
    private BigDecimal price;
    private boolean active;
}
