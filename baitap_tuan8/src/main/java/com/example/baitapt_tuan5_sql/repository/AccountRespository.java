package com.example.baitapt_tuan5_sql.repository;

import com.example.baitapt_tuan5_sql.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AccountRespository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
}
