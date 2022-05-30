package com.demo.wallet.service;

import com.demo.wallet.WalletApp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class WalletServiceImplTest {

    @Autowired
    WalletService walletService;

    private List<Integer> notesRequired;

    @BeforeEach
    void setUp() {
        notesRequired = new ArrayList<>();
    }

    @Test
    public void getNotesForAmountHappyPath() throws Exception {

        int notesInWallet[] =  {10, 1, 5, 5, 5, 1,1,1};
        int value = 16;

        List<Integer> wallet=new ArrayList<>();
        Arrays.stream(notesInWallet).forEach(k -> wallet.add(k));

        assertEquals(walletService.getNotesForAmount(wallet, notesRequired, value).size(), 3);


    }
    @Test
    public void getNotesForAmountEmptyIfSumExceedsValue() throws Exception {

        int notesInWallet[] =  {10};
        int value = 16;

        List<Integer> wallet=new ArrayList<>();
        Arrays.stream(notesInWallet).forEach(k -> wallet.add(k));

        assertEquals(walletService.getNotesForAmount(wallet, notesRequired, value).size(), 0);


    }
}