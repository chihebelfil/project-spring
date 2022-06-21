package org.yemina.Dto;


import org.springframework.stereotype.Component;
import org.yemina.Entities.Category;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class CategoryDtoMapper {

    public CategoryDto entityToDto (Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setCategoryName(category.getCategoryName());
        categoryDto.setImageUrl(category.getImageUrl());
        categoryDto.setShopKeepers(category.getShopKeepers());
        return categoryDto;
    }

    public Category DtoToEntity (CategoryDto categoryDto){
        Category category=new Category();
        category.setId(categoryDto.getId());
        category.setCategoryName(categoryDto.getCategoryName());
        category.setImageUrl(categoryDto.getImageUrl());
        category.setShopKeepers(categoryDto.getShopKeepers());
        return category;
    }

    public Collection<CategoryDto> entityToDtoList(Collection<Category> categories) {
        return
                categories.stream().map((x) -> entityToDto(x)).collect(Collectors.toList());
    }

    public Collection<Category> DtoToEntityList(Collection<CategoryDto> categoryDtos) {
        return
                categoryDtos.stream().map((x) -> DtoToEntity(x)).collect(Collectors.toList());

    }
}
