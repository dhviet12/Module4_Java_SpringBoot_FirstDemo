package com.codegym.c1020g1.service.account;

import com.codegym.c1020g1.model.UserAccount;

public interface IUserAccountService {
    UserAccount getAccountByName(String name);
    UserAccount getCurrentAccount();
}
