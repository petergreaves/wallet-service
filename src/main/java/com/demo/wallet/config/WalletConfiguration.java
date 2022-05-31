package com.demo.wallet.config;

import com.demo.wallet.model.Wallet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WalletConfiguration {

    @Bean(name="wallet")
    public Wallet getWallet(){
        return Wallet.builder().note(10).note(10).note(1).build();
    }
}
