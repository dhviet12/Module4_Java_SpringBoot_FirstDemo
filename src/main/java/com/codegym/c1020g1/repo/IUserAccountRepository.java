package com.codegym.c1020g1.repo;

import com.codegym.c1020g1.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserAccountRepository extends JpaRepository<UserAccount, Long> {
    UserAccount getUserAccountByName(String name);
}
