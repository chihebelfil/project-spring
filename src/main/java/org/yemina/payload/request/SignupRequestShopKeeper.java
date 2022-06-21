package org.yemina.payload.request;

import com.mongodb.lang.Nullable;
import org.yemina.Entities.Category;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

public class SignupRequestShopKeeper {

    @NotBlank
    //@Size(min = 3, max = 20)
    private String username;

    @NotBlank
    //@Size(max = 50)
    //@Email
    private String email;

    private Set<String> role;
    private Collection<Category> category;

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Nullable
    //@Size(min = 6, max = 40)
    private String password;

    @Nullable
    private int   taxIdentificationNumber;
    private String brandName;

    public String getState() {
        return state;
    }

    private String state;
    private String country;
    @Nullable
    private String city;
    private int phoneNumber;
    @Nullable
    private String logo;
    @Nullable
    private float tva ;
    private boolean is_Activate;

    public Set<String> getRole() {
        return role;
    }

    public Collection<Category> getCategory() {
        return category;
    }

    public int getTaxIdentificationNumber() {
        return taxIdentificationNumber;
    }

    public String getBrandName() {
        return brandName;
    }


    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getLogo() {
        return logo;
    }

    public float getTva() {
        return tva;
    }


    public boolean isIs_Activate() {
        return is_Activate;
    }
}
