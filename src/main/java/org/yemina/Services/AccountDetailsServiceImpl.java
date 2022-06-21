package org.yemina.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.yemina.Entities.Account;

import org.yemina.Entities.Category;
import org.yemina.Repository.AccountRepository;

@Service
public class AccountDetailsServiceImpl  implements UserDetailsService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return AccountDetailsImpl.build(account);
    }
    public Account Update(Account account) {
        Account updated = accountRepository.findById(account.getId()).get();
        //updated.setCreationDate(account.getCreationDate());
        updated.setEmail(account.getEmail());
        updated.setUsername(account.getUsername());
        return accountRepository.save(updated);
    }
}
