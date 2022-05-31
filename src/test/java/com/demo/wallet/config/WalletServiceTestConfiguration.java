package com.demo.wallet.config;

import com.demo.wallet.model.Wallet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WalletServiceTestConfiguration {

    @Bean
    public Wallet testWallet(){

        return Wallet.builder().note(20).note(5).note(1).build();
    }
}
