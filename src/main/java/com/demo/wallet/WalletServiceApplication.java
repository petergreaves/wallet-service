package com.demo.wallet;

import com.demo.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class WalletServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(WalletServiceApplication.class, args);
	}
}
