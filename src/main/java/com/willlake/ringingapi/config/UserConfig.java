package com.willlake.ringingapi.config;

import com.willlake.ringingapi.user.data.PasswordAuth;
import com.willlake.ringingapi.user.data.UserRepoControl;
import com.willlake.ringingapi.user.data.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    @Bean
    public UserRepoControl userRepoControl(UserRepository userRepository, PasswordAuth passwordAuth){
        return new UserRepoControl(userRepository, passwordAuth);
    }

    @Bean
    public PasswordAuth passwordAuth(){
        return new PasswordAuth();
    }
}
