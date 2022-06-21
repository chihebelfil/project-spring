package org.yemina.InterfacesMetier;

import org.yemina.Entities.Customer;

import java.util.Collection;

public interface CustomerInterface {
    public Collection<Customer> getAll();
    public Customer getId(String id);
    public void delete (String id);
    public Customer add(Customer c);
    public Customer update(Customer c);
    public void Delete(String id);
    //public Collection<FidelityCard> getFidelityCardByCustomerUsername(String username);
    public Customer findCustomerByAccount_Id(String id);
    int  Numbre_Customer();
}
