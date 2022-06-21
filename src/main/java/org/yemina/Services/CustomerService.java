package org.yemina.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yemina.Entities.Account;
import org.yemina.Entities.Customer;
import org.yemina.Entities.Rate;
import org.yemina.InterfacesMetier.CustomerInterface;

import org.yemina.Repository.AccountRepository;
import org.yemina.Repository.CustomerRepository;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Service
public class CustomerService implements CustomerInterface {


    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AccountRepository accountRepository;

    @Override
    public Collection<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getId(String id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public void delete(String id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer add(Customer c) {
        return customerRepository.save(c);
    }

    public Account updateAccount(Account account) {
        Account updated = accountRepository.findById(account.getId()).get();
        //updated.setCreationDate(account.getCreationDate());
        updated.setEmail(account.getEmail());
        updated.setUsername(account.getUsername());
        return accountRepository.save(updated);
    }
    @Override
    public void Delete(String id) {
    customerRepository.deleteAccountById(id);
    }
    @Override
    public Customer update(Customer c) {
        Customer updated = customerRepository.findById(c.getId()).get();
        Account account= updateAccount(c.getAccount());
        updated.setAccount(account);
        updated.setFirstName(c.getFirstName());
        updated.setLastName(c.getLastName());
        updated.setCddress(c.getCddress());
        updated.setContry(c.getContry());
        updated.setCity(c.getCity());
        updated.setPhoneNumber(c.getPhoneNumber());
        updated.setBirthDay(c.getBirthDay());
        updated.setGender(c.getGender());
        updated.setPostalCode(c.getPostalCode());
        return customerRepository.save(updated);
    }

    /*@Override
    public Collection<FidelityCard> getFidelityCardByCustomerUsername(String username) {
        return customerRepository.findFirstByAccountUsername(username).getFidelityCards();
    }*/

    @Override
    public Customer findCustomerByAccount_Id(String id) {
        return customerRepository.findCustomerByAccount_Id(id);
    }

    @Override
    public int Numbre_Customer() {
        List<Customer> listCustomer = new LinkedList<>();
        listCustomer = customerRepository.findAll();
        return listCustomer.size();
    }
}
