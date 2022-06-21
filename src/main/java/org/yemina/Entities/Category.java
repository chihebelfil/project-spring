package org.yemina.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Category  implements Serializable{
	@Id
	private String id;
	private String categoryName;
	private String imageUrl;

	@JsonIgnore
	@DBRef(lazy = true)
	private Collection<ShopKeeper> shopKeepers = new ArrayList<ShopKeeper>();


	   
	public Category() {
		super();
	}

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
