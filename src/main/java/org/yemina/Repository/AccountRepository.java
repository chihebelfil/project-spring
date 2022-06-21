package org.yemina.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.yemina.Entities.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends MongoRepository<Account, String > {
    Optional<Account> findByUsername(String username);
    Account findAccountByEmail(String email);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    void deleteAccountById(String id);



}
