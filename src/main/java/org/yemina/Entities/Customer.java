package org.yemina.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.validation.constraints.NotNull;


@Document
public class Customer  implements Serializable {
	 
	   @Id
       private String id;
	   private String firstName;
	   private String lastName;
	   private String cddress;
	   private String contry;
	   private String city;
	   private int phoneNumber;
	   private Date birthDay;
	   private String gender;
	   private int postalCode;
	   private String status;
	   private String imageName;
	   @NotNull
	   @DBRef(lazy = true)
	   private Account account;
	   @JsonIgnore
	   private Collection<Rate> rates;


	   public void setRates(Collection<Rate> rates) { this.rates = rates; }

	   public Collection<Rate> getRates() { return rates; }

	   
	public Customer() {
		super();
	}

	public Customer(Collection<Rate> rates,String id, String firstName, String lastName, String cddress, String contry, String city, int phoneNumber, Date birthDay, String gender, int postalCode, String status, Account account) {
		this.rates= rates;
	   	this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.cddress = cddress;
		this.contry = contry;
		this.city = city;
		this.phoneNumber = phoneNumber;
		this.birthDay = birthDay;
		this.gender = gender;
		this.postalCode = postalCode;
		this.status = status;
		this.account = account;
		//this.fidelityCards = fidelityCards;
		//this.favorises = favorises;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCddress() {
		return cddress;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public void setCddress(String cddress) {
		this.cddress = cddress;
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

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	/*public Collection<FidelityCard> getFidelityCards() {
		return fidelityCards;
	}

	public void setFidelityCards(Collection<FidelityCard> fidelityCards) {
		this.fidelityCards = fidelityCards;
	}

	public Collection<Favoris> getFavorises() {
		return favorises;
	}

	public void setFavorises(Collection<Favoris> favorises) {
		this.favorises = favorises;
	}*/
}
