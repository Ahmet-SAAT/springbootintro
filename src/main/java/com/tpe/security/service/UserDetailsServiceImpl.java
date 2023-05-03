package com.tpe.security.service;

import com.tpe.domain.Role;
import com.tpe.domain.User;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {
  //bu classda 1. amacimiz securitye user verecegim ve bu user userdetailse cevrilecek.
    //kendi userlarimi securty katmanina tanitmis olacagiz
//2. amacimiz rollerimizi GrantedAuthoritye cevirmek.

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user =  userRepository.findByUserName(username).orElseThrow(
                ()->new ResourceNotFoundException("user not found with username : "+username));
      if (user!=null){
          return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),builGrantedAuthority(user.getRole()));
      }else {
          throw new UsernameNotFoundException("username not found with username : " +username);
      }

    }

    private static List<SimpleGrantedAuthority> builGrantedAuthority(final Set<Role> roles){
        List<SimpleGrantedAuthority> authorities=new ArrayList<>();
        for (Role role:roles){
            authorities.add(new SimpleGrantedAuthority(role.getName().name()));
        }
        return authorities;
    }

}
