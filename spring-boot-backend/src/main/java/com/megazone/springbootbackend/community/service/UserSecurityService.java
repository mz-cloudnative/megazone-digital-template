package com.megazone.springbootbackend.community.service;

import com.megazone.springbootbackend.community.model.entity.Users;
import com.megazone.springbootbackend.community.repository.UserRepository;
import com.megazone.springbootbackend.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private String username;

    @Override
    public UserDetails loadUserByUsername(String username) {

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        Users user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("일치하는 사용자 부재"));

        List<String> listRoles = new ArrayList<>();
        //List<UserRoles> userRoles = user.getUserRoleList();

        user.getAuthorities().forEach(role->listRoles.add(role.getAuthorityName()));// get role from database - usa il tuo modo **

        grantedAuthorityList = listRoles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        //*** important ****
        return new org.springframework.security.core.userdetails.User(username,
                user.getPassword(), grantedAuthorityList);
    }

}
