package org.yemina.payload.request;




import com.mongodb.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.*;

public class SignupRequestCustomer {
    @NotBlank
   // @Size(min = 3, max = 20)
    private String username;

    @NotBlank
   // @Size(max = 50)

    private String email;

    private Set<String> role;

    @NotBlank
   // @Size(min = 6, max = 40)
    private String password;

    public Set<String> getRole() {
        return role;
    }

    @NotBlank
   // @Size(min = 6, max = 40)
    private String firstName;
    @NotBlank
   // @Size(min = 6, max = 40)
    private String lastName;
    @Nullable
    //@Size(min = 6, max = 40)
    private String cddress;
    @Nullable
    //@Size(min = 6, max = 40)
    private String contry;
    @Nullable
    //@Size(min = 6, max = 40)
    private String city;

    private int phoneNumber;
    //@NotBlank

    //private Date birthDay;
    @Nullable
    //@Size(min = 6, max = 40)
    private String gender;

    private int postalCode;
    @Nullable
    //@Size(min = 6, max = 40)
    private String status;

    private Date birthday;

    public Date getBirthday() {
        return birthday;
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
/*
    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
*/
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

   /*
    private String numTel ;

    private String nomP;
    private String prenomP;

    private Boolean active ;
    private Boolean otpConfirmation;

    private Date dataNaissance ;


   //-------------ClientFields-----------------
   private String adressePersonnelle;
    private String codePostalPersonnelle;
    private String ville;
    private String gouvernorat;
    private String numPermis;
    private Date dateObtention;
    private String adresseProfessionnelle;
    private String codePostalProfessionnelle;
    private String villeProfessionnelle;
    private String gouvernoratProfessionnelle;
    private String emailProfessionnelle;
    private String raisonSocial ;
    private String MF ;
    private String RegistreDeCommerce ;
    private Long rib ;
    private Long cin ;
    private String numPolice ;

*/


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



}
