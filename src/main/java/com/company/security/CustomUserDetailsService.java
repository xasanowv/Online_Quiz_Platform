package com.company.security;

import com.company.exception.InvalidPasswordOrGmailException;
import com.company.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private  final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String gmail) throws UsernameNotFoundException {

        return userRepository.
                findByGmail(gmail)
                .orElseThrow(InvalidPasswordOrGmailException::new);//xato invalid gmail
    }
}
