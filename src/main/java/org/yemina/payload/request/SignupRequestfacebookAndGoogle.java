package org.yemina.payload.request;

import javax.validation.constraints.NotBlank;

public class SignupRequestfacebookAndGoogle {


    @NotBlank
    // @Size(max = 50)

    private String email;




    @NotBlank
    // @Size(min = 6, max = 40)
    private String firstName;
    @NotBlank
    // @Size(min = 6, max = 40)
    private String lastName;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}
