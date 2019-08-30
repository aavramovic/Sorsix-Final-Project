package com.sorsix.finkicommunity.security;

import com.sorsix.finkicommunity.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserPrincipalDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public UserPrincipalDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserPrincipal userPrincipal = this.userRepository.findByUsername(username)
                .map(
                        user -> new UserPrincipal(user)
                ).orElseThrow(
                        () -> new UsernameNotFoundException("No user found with username " + username)
                );
        return userPrincipal;
    }
}
