package org.yemina.Dto;

import java.sql.Date;

public class DiscountDto {
    private Long id;
    private int   Percentage;
    private Date dateCreation;
    private Date dateExpiration;
    private int   numberOfCoupons;
    private String description;
    //private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPercentage() {
        return Percentage;
    }

    public void setPercentage(int percentage) {
        Percentage = percentage;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public int getNumberOfCoupons() {
        return numberOfCoupons;
    }

    public void setNumberOfCoupons(int numberOfCoupons) {
        this.numberOfCoupons = numberOfCoupons;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
