package com.codegym.c1020g1.service.account;

import com.codegym.c1020g1.model.UserAccount;
import com.codegym.c1020g1.repo.IUserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAccountService implements IUserAccountService, UserDetailsService {
    @Autowired
    IUserAccountRepository userAccountRepository;

    @Override
    public UserAccount getAccountByName(String name) {
        return userAccountRepository.getUserAccountByName(name);
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = this.getAccountByName(username);
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(userAccount.getRole());
        return new User(
                userAccount.getName(),
                userAccount.getPassword(),
                authorityList
        );
    }

    @Override
    public UserAccount getCurrentAccount() {
        UserAccount userAccount;
        String name;
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(object instanceof UserDetails){
            name = ((UserDetails) object).getUsername();
        } else{
            name = object.toString();
        }
        userAccount = this.getAccountByName(name);
        return userAccount;
    }

    @Override
    public UserAccount addAccount(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }
}
