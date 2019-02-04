package com.oleszeksylwester.dmssb.DMSSB.config;

import com.oleszeksylwester.dmssb.DMSSB.service.UserService;
import com.oleszeksylwester.dmssb.DMSSB.serviceimpl.UserServiceImpl;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class UserServiceTestConfiguration {
    @Bean
    @Primary
    public UserServiceImpl userService() {
        return Mockito.mock(UserServiceImpl.class);
    }
}
