package com.workspace.shopping.product;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toDto(Product product);


    @Mapping(target = "id", ignore = true)
    Product toEntity(ProductDTO productDTO);
}
