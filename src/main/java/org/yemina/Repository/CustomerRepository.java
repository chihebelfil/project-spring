package org.yemina.Repository;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.yemina.Entities.Customer;

import java.util.List;

public interface CustomerRepository extends MongoRepository<Customer, String > {

    Customer findFirstByAccountUsername(String username);
    Customer findCustomerByAccount_Id(String id);
    void deleteAccountById(String id);
}
