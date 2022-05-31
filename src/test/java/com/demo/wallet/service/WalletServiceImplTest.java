package com.demo.wallet.service;

import com.demo.wallet.config.WalletServiceTestConfiguration;
import com.demo.wallet.model.Wallet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Import(WalletServiceTestConfiguration.class)
class WalletServiceImplTest {


    @Autowired
    Wallet testWallet;

    WalletService walletService;

    private List<Integer> notesRequired;

    @BeforeEach
    void setUp() {

        notesRequired = new ArrayList<>();
        walletService = new WalletServiceImpl(testWallet);
    }

    @Test
    public void getNotesForAmountHappyPath()  {

        List<Integer> ret = walletService.getNotesForAmount(21);

        Assertions.assertAll(
                () -> {
                    assertEquals(ret.size(), 2);
                },
                () -> {
                    assertEquals(ret.get(0), 20);
                },
                () -> {
                    assertEquals(ret.get(1), 1);
                }
        );
    }

    @Test
    public void getNotesForAmountEmptyIfSumExceedsValue()  {
        assertEquals(walletService.getNotesForAmount(999).size(), 0);

    }

    @Test
    public void getNotesForAmountEmptyIfValueIsZero()  {
        assertEquals(walletService.getNotesForAmount(0).size(), 0);

    }

    @Test
    public void getNotesForAmountEmptyIfValueIsNegative()  {
        assertEquals(walletService.getNotesForAmount(-1).size(), 0);

    }

}