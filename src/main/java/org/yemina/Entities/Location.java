package org.yemina.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
@Document
public class Location implements Serializable {
    @Id
    private String id;

    private float lat;
    private float lng;
    @DBRef(lazy = true)
    private ShopKeeper shopkeeper;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /*public ShopKeeper getShopkeeper() {
        return shopkeeper;
    }

    public void setShopkeeper(ShopKeeper shopkeeper) {
        this.shopkeeper = shopkeeper;
    }
    */
    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public ShopKeeper getShopKeeper() {
        return shopkeeper;
    }

    public void setShopKeeper(ShopKeeper shopKeeper) {
        this.shopkeeper = shopKeeper;
    }

    public Location(String id, float lat, float lng, ShopKeeper shopKeeper) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.shopkeeper = shopKeeper;
    }

    public Location() {
    }
}
