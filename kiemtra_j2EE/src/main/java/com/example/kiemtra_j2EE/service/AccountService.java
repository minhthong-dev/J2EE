package com.example.kiemtra_j2EE.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.kiemtra_j2EE.models.Account;
import com.example.kiemtra_j2EE.repository.AccountRepository;

@Service
public class AccountService implements UserDetailsService {

        @Autowired
        private AccountRepository accountRepository;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Account account = accountRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException(
                                                "Không tìm thấy người dùng: " + username));
                System.out.println("day la role dang nhap" + account.getRoles());
                return new User(
                                account.getUsername(),
                                account.getPassword(),
                                account.getRoles().stream()
                                                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                                                .collect(Collectors.toList()));
        }
}