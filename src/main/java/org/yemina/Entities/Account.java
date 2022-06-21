package org.yemina.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mongodb.lang.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Document
public class Account  implements Serializable {
	   @Id
	   private String id;
	   private Date  creationDate;
	   private String username;
	   private String email;
	   private String password;


       @JsonIgnore
	   @DBRef(lazy = true)
	   private ShopKeeper shopKeeper;
	   @Nullable
	   @DBRef(lazy = true)
	   private Customer customer;
	@JsonIgnore
	public ShopKeeper getShopKeeper() {
		return shopKeeper;
	}

	public void setShopKeeper(ShopKeeper shopKeeper) {
		this.shopKeeper = shopKeeper;
	}
	@JsonIgnore
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	private Set<Role> roles = new HashSet<>();
  	public Account() {
		super();
		}
	public Account(Date creationDate, String email) {
		this.creationDate = creationDate;
		this.email = email;
	}

	public Account(Date creationDate, String username, String email, String password) {

		this.creationDate = creationDate;
		this.username = username;
		this.email = email;
		this.password = password;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
