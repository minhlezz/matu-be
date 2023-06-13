package com.workspace.shopping.product;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ProductDTO {

    private long id;

    private String name;

    private String description;

    @Min(value = 0, message = "Price must be > 0")
    private double price;

    @Min(value = 1)
    private int quantity;
}
