package org.yemina.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;


@Document
public class Rate  implements Serializable {
    @Id
    private String id;
    private int rateNumber;
    @DBRef(lazy = true)
    private ShopKeeper shopkeeper;
    @DBRef(lazy = true)
    private Customer customer;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRateNumber() {
        return rateNumber;
    }

    public void setRateNumber(int rateNumber) {
        this.rateNumber = rateNumber;
    }

    public ShopKeeper getShopkeeper() {
        return shopkeeper;
    }

    public void setShopkeeper(ShopKeeper shopkeeper) {
        this.shopkeeper = shopkeeper;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public Rate(String id, int rateNumber, ShopKeeper shopkeeper, Customer customer) {
        this.id = id;
        this.rateNumber = rateNumber;
        this.shopkeeper = shopkeeper;
        this.customer = customer;
    }
}
