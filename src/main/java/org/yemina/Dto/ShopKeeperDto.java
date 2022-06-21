package org.yemina.Dto;

public class ShopKeeperDto {
    private String id_ShopKeeper;
    private int   taxIdentificationNumber;
    private String brandName;
    private int phoneNumber;
    private String logo;

    public String getId_ShopKeeper() {
        return id_ShopKeeper;
    }

    public void setId_ShopKeeper(String id_ShopKeeper) {
        this.id_ShopKeeper = id_ShopKeeper;
    }

    public int getTaxIdentificationNumber() {
        return taxIdentificationNumber;
    }

    public void setTaxIdentificationNumber(int taxIdentificationNumber) {
        this.taxIdentificationNumber = taxIdentificationNumber;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
