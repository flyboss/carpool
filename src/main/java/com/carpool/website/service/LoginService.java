package com.carpool.website.service;

import com.carpool.domain.UserEntity;
import com.carpool.website.dao.UserEntityRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

/**
 * Created by deado on 2016/12/15.
 */
public class LoginService implements UserDetailsService {


    private final UserEntityRepository userEntityRepository;

    public LoginService(UserEntityRepository uER){
        this.userEntityRepository = uER;
    }



    @Override
    public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException{
        UserEntity userEntity = this.userEntityRepository.findById(username);
        if(null != userEntity){
            ArrayList<GrantedAuthority> authorities =
                    new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            return  new User (
                    userEntity.getId(),
                    userEntity.getPassword(),
                    authorities
                    );
        }

        throw new UsernameNotFoundException(
                "User '" + username + "not found"
        );

    }
}
