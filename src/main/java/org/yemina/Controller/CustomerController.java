package org.yemina.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yemina.Entities.Customer;
import org.yemina.InterfacesMetier.CustomerInterface;

import java.util.Collection;

@RestController
@RequestMapping(value ="customer")
@CrossOrigin(origins = "*")
public class CustomerController {
    @Autowired
    private CustomerInterface customerInterface;

    @PostMapping("/get")
    public Collection<Customer> getAll(){
        return customerInterface.getAll();
    }

    @GetMapping("/get/{id}")
    Customer getOne(@PathVariable String id) {
        return customerInterface.getId(id);
    }

    @PutMapping("/update")
    public Customer ModifierShopkeeper(@RequestBody Customer customer){
        if (customer != null) {
            return customerInterface.update(customer);
        }
        return null;
    }

    /*@GetMapping("/get-customer-fidelity-cards/{username}")
    public Collection<FidelityCard> getCustomerFidelityCards(@PathVariable String username){
        System.out.println("String ="+username);
        return customerInterface.getFidelityCardByCustomerUsername(username);
    }*/

    @GetMapping("/get-customer-id/{id}")
    public Customer getCustomerByUsername(@PathVariable String id){
        System.out.println("String ="+id);
        Customer customer = customerInterface.findCustomerByAccount_Id(id);
        //System.out.println("Customer = "+customer.toString());
        return customerInterface.findCustomerByAccount_Id(id);
    }

    @DeleteMapping("/suup/{id}")
    public void deleteCategory(@PathVariable String id){
        customerInterface.Delete(id);
    }

    @GetMapping("/numbreCustomer")
    public int numbre_Customer() {
        return customerInterface.Numbre_Customer();
    }
}
