package com.demo.wallet.controller;

import com.demo.wallet.service.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(WalletController.BASE_URL)
public class WalletController {

    private WalletService walletService;


    public static final String BASE_URL="/wallet";

    public WalletController(WalletService walletService) {
        this.walletService = walletService;

    }

    @GetMapping({"/check-funds/{amount}"})
    public ResponseEntity<List<Integer>> checkFunds(@PathVariable Integer amount){

        if (amount <1){
            log.error("Value can't be less than 0");
            return ResponseEntity.badRequest().build();
        }

        List<Integer> ret = walletService.getNotesForAmount(amount);
        return ResponseEntity.ok().body(ret);

    }


}
