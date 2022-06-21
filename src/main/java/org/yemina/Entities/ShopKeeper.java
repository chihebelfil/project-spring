package org.yemina.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mongodb.lang.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Document
public class ShopKeeper  implements Serializable {
	  
	   @Id
	   private String id;
	   private int   taxIdentificationNumber;
	   private String brandName;
	   private String address;
	   private String contry;
	   private String city;
	   private int phoneNumber;
	   private String logo;
		private String patente;
	   private float tva ;
	   private boolean is_Activate ;
	   private float tauxRemise;
	   private float tauxConversion;

	@NotNull
	   @DBRef(lazy = true)
	   private Account account;

	   @Nullable
	   @DBRef(lazy = true)
	   private Collection<Category> category = new ArrayList<Category>();
	   
	   /*@OneToMany(mappedBy = "shopKeeper_Product", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	   private Collection<Product> product;

	   @Nullable
	   @OneToMany(mappedBy = "shopKeeper", fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
	   private Collection<FidelityCard> fidelityCard_Shopkeeper;

	   @Nullable
	   @JsonIgnore
	   @OneToMany(mappedBy = "shopKeeper", fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
	   private Collection<Favoris> favorises ;*/

	   @JsonIgnore
	   private Collection<Location> locations;
	   @JsonIgnore
	   private Collection<Rate> rates;

	public void setRates(Collection<Rate> rates) { this.rates = rates; }

	public Collection<Rate> getRates() { return rates; }
	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public ShopKeeper() {
		super();
	}

	public ShopKeeper(Collection<Rate> rates,Collection<Location> locations,String id_ShopKeeper, int taxIdentificationNumber, String brandName, String address, String contry, String city, int phoneNumber, String logo, float tva, Account account, Collection<Category> category,
					  float tauxRemise, float tauxConversion) {
		this.rates=rates;
		this.locations=locations;
		this.id = id_ShopKeeper;
		this.taxIdentificationNumber = taxIdentificationNumber;
		this.brandName = brandName;
		this.address = address;
		this.contry = contry;
		this.city = city;
		this.phoneNumber = phoneNumber;
		this.logo = logo;
		this.tva = tva;
		this.is_Activate = is_Activate;
		this.account = account;
		this.category = category;
		//this.product = product;
		//this.fidelityCard_Shopkeeper = fidelityCard_Shopkeeper;
		//this.favorises = favorises;
	}

	public boolean isIs_Activate() {
		return is_Activate;
	}

	public void setIs_Activate(boolean is_Activate) {
		this.is_Activate = is_Activate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getTaxIdentificationNumber() {
		return taxIdentificationNumber;
	}

	public void setTaxIdentificationNumber(int taxIdentificationNumber) {
		this.taxIdentificationNumber = taxIdentificationNumber;
	}

	public Collection<Location> getLocations() {
		return locations;
	}

	public void setLocations(Collection<Location> locations) {
		this.locations = locations;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContry() {
		return contry;
	}

	public void setContry(String contry) {
		this.contry = contry;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public float getTva() {
		return tva;
	}

	public void setTva(float tva) {
		this.tva = tva;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
    
	public Collection<Category> getCategory() {
		return category;
	}

	public void setCategory(Collection<Category> category) {
		this.category = category;
	}

	public float getTauxRemise() {
		return tauxRemise;
	}

	public void setTauxRemise(float tauxRemise) {
		this.tauxRemise = tauxRemise;
	}

	public float getTauxConversion() {
		return tauxConversion;
	}

	public void setTauxConversion(float tauxConversion) {
		this.tauxConversion = tauxConversion;
	}

	@Override
	public String toString() {
		return "ShopKeeper{" +
				"id=" + id +
				", taxIdentificationNumber=" + taxIdentificationNumber +
				", brandName='" + brandName + '\'' +
				", address='" + address + '\'' +
				", contry='" + contry + '\'' +
				", city='" + city + '\'' +
				", phoneNumber=" + phoneNumber +
				", logo='" + logo + '\'' +
				", tva=" + tva +
				", is_Activate=" + is_Activate +
				", account=" + account +
				", category=" + category +
				", "+
				'}';
	}
}
