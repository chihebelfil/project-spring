package org.yemina.Dto;

import org.yemina.Entities.ShopKeeper;

import java.util.Collection;

public class CategoryDto {

    private String id;
    private String categoryName;
    private String imageUrl;
    private Collection<ShopKeeper> shopKeepers;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Collection<ShopKeeper> getShopKeepers() {
        return shopKeepers;
    }

    public void setShopKeepers(Collection<ShopKeeper> shopKeepers) {
        this.shopKeepers = shopKeepers;
    }
}
